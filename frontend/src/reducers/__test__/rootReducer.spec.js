jest.unmock('../rootReducer');

import * as types from '../../actions/types'
import * as reducers from '../rootReducer';

describe('rootReducer', () => {
    describe('pairingList', () => {
        it('has a default state', () => {
            expect(reducers.pairingList()).toEqual([]);
        });

        it('changes state on PAIRING_LIST action', () => {
            expect(reducers.pairingList([], {
                type: types.PAIRING_LIST,
                pairingList: [{first: "hi"}]
            })).toEqual([{first: "hi"}]);
        });
    });
});