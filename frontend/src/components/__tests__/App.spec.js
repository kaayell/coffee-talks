jest.unmock('../App');

import React from 'react';
import {shallow, mount, render} from 'enzyme';
import {App} from "../App"
import {Header} from "../Header";

describe('App', () => {
    let wrapper;
    let fetchPairingList;
    let fetchNewPairs;
    let recordPairingList;

    beforeEach(() => {
        fetchPairingList = jest.fn();
        fetchNewPairs = jest.fn();
        recordPairingList = jest.fn();
        let pairingList = [
            {
                "first": {
                    "email": "gene@burger.com",
                    "name": "Gene Belcher"
                },
                "second": {
                    "email": "tina@burger.com",
                    "name": "Tina Belcher"
                }
            },
            {
                "first": {
                    "email": "bob@burger.com",
                    "name": "Bob Belcher"
                },
                "second": {
                    "email": "linda@burger.com",
                    "name": "Linda Belcher"
                }
            },
        ];
        wrapper = mount(<App
            fetchLatestPairingList={fetchPairingList}
            fetchNewPairs={fetchNewPairs}
            pairingList={pairingList}
            recordPairingList={recordPairingList}
            pairingListId={"id"}
        />);
    });

    it('fetches pairing list on mount', () => {
        expect(fetchPairingList).toHaveBeenCalled()
    });

    it('renders header', () => {
        expect(wrapper.find('Header').node).toBeDefined();
    });

    it('renders list', () => {
        let list = wrapper.find('.pair-container');
        expect(list.length).toBe(2);
        expect(list.at(0).text()).toContain("Gene Belcher");
        expect(list.at(0).text()).toContain("Tina Belcher");

        expect(list.at(1).text()).toContain("Bob Belcher");
        expect(list.at(1).text()).toContain("Linda Belcher")
    });

    it('dispatches new pairs action on click of new pairs button', () => {
        let newPairsButton = wrapper.find('.new-pairs-button');
        newPairsButton.simulate('click');
        expect(fetchNewPairs).toBeCalled();
    });

    it('dispatches record pairs action on click of record pairs button', () => {
        let newPairsButton = wrapper.find('.record-pairs-button');
        newPairsButton.simulate('click');
        expect(recordPairingList).toBeCalledWith("id");
    });
});