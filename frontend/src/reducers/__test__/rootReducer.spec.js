jest.unmock('../rootReducer');

import * as types from '../../actions/types'
import * as reducers from '../rootReducer';

describe('rootReducer', () => {
    describe('storePairingList', () => {
        it('has a default state', () => {
            expect(reducers.pairingList()).toEqual([]);
        });

        it('changes state on STORE_PAIRING_LIST action', () => {
            expect(reducers.pairingList([], {
                type: types.STORE_PAIRING_LIST,
                pairingList: [{first: "hi"}]
            })).toEqual([{first: "hi"}]);
        });
    });

    describe('humans', () => {
        it('has a default state', () => {
            expect(reducers.humans()).toEqual([]);
        });

        it('changes state on STORE_PAIRING_LIST action', () => {
            expect(reducers.humans([], {
                type: types.STORE_HUMANS,
                humans: [{yo: "hi"}]
            })).toEqual([{yo: "hi"}]);
        });
    });
});