jest.unmock('../App');

import React from 'react';
import {shallow, mount, render} from 'enzyme';
import {App} from "../App"
import {Header} from "../Header";
jest.mock('react-materialize');

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
        wrapper = shallow(<App
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
        let list = wrapper.find('.pair-container .pair-name');
        expect(list.length).toBe(4);
        expect(list.at(0).text()).toContain("Gene Belcher");
        expect(list.at(1).text()).toContain("Tina Belcher");

        expect(list.at(2).text()).toContain("Bob Belcher");
        expect(list.at(3).text()).toContain("Linda Belcher")
    });
});