jest.unmock('../rootSaga');

import * as sagas from "../rootSaga";
import {call, put} from "redux-saga/effects";
import {apiGet} from "../../api/api";
import {pairingList} from "../../actions/actions";

describe('rootSaga', () => {

    describe('watchFetchLatestPairingList', () => {
        it('watches FETCH_LATEST_PAIRING_LIST', () => {
            let iterator = sagas.watchFetchLatestPairingList();
            let saga = iterator.next();
            // expect(saga.value).toEqual(
            //     takeEvery(actionTypes.FETCH_LATEST_PAIRING_LIST,
            //         sagas.fetchLatestPairingList))
        });
    });

    describe('fetchLatestPairingList', () => {
        it('calls api', () => {
            let iterator = sagas.fetchLatestPairingList();
            let saga = iterator.next();
            expect(saga.value).toEqual(call(apiGet, '/pair/latest'))
        });

        it('dispatches pairing list', () => {
            let iterator = sagas.fetchLatestPairingList();
            iterator.next();
            let saga = iterator.next({data: {pairingList: "hi"}});
            expect(saga.value).toEqual(put(pairingList("hi")))
        });
    });

});