jest.unmock('../Header');

import React from "react";
import {shallow} from "enzyme";
import {Header} from "../Header";

describe('Header', () => {
    let wrapper;
    let fetchPairingList;

    beforeEach(() => {
        fetchPairingList = jest.fn();
        wrapper = shallow(<Header/>);
    });

    it('renders', () => {
        let nav = wrapper.find('.nav');
        expect(nav.length).toBe(1);
        expect(nav.text()).toContain("Coffee Talks");
    })
});