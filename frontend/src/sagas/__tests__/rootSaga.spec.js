jest.unmock('../rootSaga');

import * as sagas from "../rootSaga";
import {call, put} from "redux-saga/effects";
import {apiGet, apiPost} from "../../api/api";
import {
    storePairingList,
    storePairingListId,
    storeHumans,
} from "../../actions/actions";

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
            expect(saga.value).toEqual(put(storePairingList("hi")))
        });
    });

    describe('fetchHumans', () => {
        it('calls api', () => {
            let iterator = sagas.fetchHumans();
            let saga = iterator.next();
            expect(saga.value).toEqual(call(apiGet, '/humans'))
        });

        it('dispatches humans', () => {
            let iterator = sagas.fetchHumans();
            iterator.next();
            let saga = iterator.next({data: []});
            expect(saga.value).toEqual(put(storeHumans([])))
        });
    });

    describe('fetchNewPairs', () => {
        it('calls api', () => {
            let iterator = sagas.fetchNewPairs();
            let saga = iterator.next();
            expect(saga.value).toEqual(call(apiGet, '/pair'))
        });

        it('dispatches id and list', () => {
            let iterator = sagas.fetchNewPairs();
            iterator.next();
            let saga = iterator.next({data: {id: "id", pairingList: "list"}});
            expect(saga.value).toEqual(put(storePairingListId("id")));
            saga = iterator.next();
            expect(saga.value).toEqual(put(storePairingList("list")));
        });
    });

    describe('recordPairingList', () => {
        it('calls api', () => {
            let iterator = sagas.recordPairingList({id: "id"});
            let saga = iterator.next();
            expect(saga.value).toEqual(call(apiPost, '/pair/record/id'))
        });
    });

    describe('addHuman', () => {
        it('calls api', () => {
            let iterator = sagas.addHuman({name: "sup", email: "email"});
            let saga = iterator.next();
            expect(saga.value).toEqual(call(apiPost, '/humans', {name: "sup", email: "email"}))
        });
    });
});