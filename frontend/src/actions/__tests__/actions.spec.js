jest.unmock('../actions');

import * as actions from '../actions';
import * as types from '../types'

describe('actions', () => {
    it('pairingList', () => {
        expect(actions.pairingList({})).toEqual(
            {type: types.PAIRING_LIST, pairingList: {}}
        )
    });

    it('fetchLatestPairingList', () => {
        expect(actions.fetchLatestPairingList()).toEqual(
            {type: types.FETCH_LATEST_PAIRING_LIST}
        );
    })
});