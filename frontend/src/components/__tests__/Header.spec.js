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
        let nav = wrapper.find('Navbar');
        expect(nav.length).toBe(1);
        expect(nav.props().brand).toContain("Coffee Talks");

        let link = wrapper.find('Link');
        expect(link.props().to).toContain("pivots");
    })
});