jest.unmock('../Humans');

import React from 'react';
import {shallow, mount, render} from 'enzyme';
import {Humans} from "../Humans"
import {Header} from "../Header";

describe('Humans', () => {
    let wrapper;
    let fetchHumans;

    beforeEach(() => {
        fetchHumans = jest.fn();
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
        wrapper = mount(<Humans
            fetchHumans={fetchHumans}
            humans={humans}
        />);
    });

    it('fetches humans on mount', () => {
        expect(fetchHumans).toHaveBeenCalled()
    });

    it('renders header', () => {
        expect(wrapper.find('Header').node).toBeDefined();
    });

    it('renders humans', () => {
        let list = wrapper.find('.human-container');
        expect(list.length).toBe(2);
        expect(list.at(0).text()).toContain("Bob Belcher");
        expect(list.at(1).text()).toContain("Tina Belcher");
    })
});