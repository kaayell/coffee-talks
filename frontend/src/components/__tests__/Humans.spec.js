jest.unmock('../Humans');

import React from 'react';
import {shallow, mount, render} from 'enzyme';
import {Humans} from "../Humans"
import {Header} from "../Header";
jest.mock('react-materialize');

describe('Humans', () => {
    let wrapper;
    let fetchHumans;
    let addHuman;

    beforeEach(() => {
        fetchHumans = jest.fn();
        addHuman = jest.fn();
        let humans = [
            {
                "email": "bob@burger.com",
                "name": "Bob Belcher"
            },
            {
                "email": "tina@burger.com",
                "name": "Tina Belcher"
            }
        ];
        wrapper = shallow(<Humans
            fetchHumans={fetchHumans}
            humans={humans}
            addHuman={addHuman}
        />);
    });

    it('fetches humans on mount', () => {
        expect(fetchHumans).toHaveBeenCalled()
    });

    it('renders header', () => {
        expect(wrapper.find('Header').node).toBeDefined();
    });

    it('renders humans', () => {
        let list = wrapper.find('.human-list-container .human-container');
        expect(list.length).toBe(2);
        // expect(list.at(0).text()).toContain("Bob Belcher");
        // expect(list.at(1).text()).toContain("Tina Belcher");
    });

    it('dispatches adding human action on click of button', () => {
        let addHumanButton = wrapper.find('.plus-button');
        addHumanButton.simulate('click');
        expect(wrapper.state().addHumanMode).toBe(true);
    });

    describe('add human', () => {
        beforeEach(() => {
            wrapper.setState({addHumanMode: true});
            wrapper.update();
        });

        it('renders when addHumanMode is true', () => {
            let nameField = wrapper.find('.add-human-name-field');
            let emailField = wrapper.find('.add-human-email-field');

            expect(nameField).toBeDefined();
            expect(emailField).toBeDefined();
        });

        it('cancels addHumanMode', () => {
            let cancelAddHuman = wrapper.find('.cancel-human-button');
            cancelAddHuman.simulate('click');

            expect(addHuman).not.toHaveBeenCalled();
            expect(wrapper.state().addHumanMode).toBe(false);
        });

        it('dispatches addHuman on click of Add', () => {
            wrapper.setState({name: "name", email: "email"});
            let confirmAddHuman = wrapper.find('.add-human-button');
            confirmAddHuman.simulate('click');

            expect(addHuman).toHaveBeenCalledWith("name", "email");
            expect(wrapper.state().addHumanMode).toBe(false);
        });
    });
});