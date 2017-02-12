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
    let deleteHuman;

    beforeEach(() => {
        fetchHumans = jest.fn();
        addHuman = jest.fn();
        deleteHuman = jest.fn();
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
            deleteHuman={deleteHuman}
        />);
    });

    it('fetches humans on mount', () => {
        expect(fetchHumans).toHaveBeenCalled()
    });

    it('renders header', () => {
        expect(wrapper.find('Header').node).toBeDefined();
    });

    it('renders humans', () => {
        let list = wrapper.find('Card');
        expect(list.length).toBe(2);
        expect(list.at(0).props().children).toContain("Bob Belcher");
        expect(list.at(1).props().children).toContain("Tina Belcher");
    });
});