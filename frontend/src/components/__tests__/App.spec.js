jest.unmock('../App');

import React from 'react';
import {shallow, mount, render} from 'enzyme';
import {App} from "../App"

describe('App', () => {
    let wrapper;
    let fetchPairingList;

    beforeEach(() => {
        fetchPairingList = jest.fn();
        let pairingList = {};
        wrapper = mount(<App
            fetchPairingList={fetchPairingList}
        />);
    });

    it('fetches pairing list on mount', () => {
        expect(fetchPairingList).toHaveBeenCalled()
    });

    it('renders list', () => {

    })
});