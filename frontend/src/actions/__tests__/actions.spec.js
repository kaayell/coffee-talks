jest.unmock('../actions');

import * as actions from '../actions';
import * as types from '../types'

describe('actions', () => {
    it('storePairingList', () => {
        expect(actions.storePairingList({})).toEqual(
            {type: types.STORE_PAIRING_LIST, pairingList: {}}
        )
    });

    it('storePairingListId', () => {
        expect(actions.storePairingListId("1")).toEqual(
            {type: types.STORE_PAIRING_LIST_ID, id: "1"}
        )
    });

    it('fetchLatestPairingList', () => {
        expect(actions.fetchLatestPairingList()).toEqual(
            {type: types.FETCH_LATEST_PAIRING_LIST}
        );
    });

    it('storeHumans', () => {
        expect(actions.storeHumans({})).toEqual(
            {type: types.STORE_HUMANS, humans: {}}
        )
    });

    it('fetchHumans', () => {
        expect(actions.fetchHumans()).toEqual(
            {type: types.FETCH_HUMANS}
        );
    });

    it('recordPairingList', () => {
        expect(actions.recordPairingList()).toEqual(
            {type: types.RECORD_PAIRING_LIST}
        );
    });

});