import {takeEvery} from 'redux-saga';
import { call, put, fork } from 'redux-saga/effects'
import * as actionTypes from '../actions/types'
import {apiGet, apiPost, apiDelete} from "../api/api"
import {
    storePairingList,
    storeHumans,
    storePairingListId
} from '../actions/actions'

export function* fetchLatestPairingList() {
    try {
        const response = yield call(apiGet, '/pair/latest');
        yield put(storePairingListId(response.data.internalId));
        yield put(storePairingList(response.data.pairingList));
    } catch (error) {}
}

export function* watchFetchLatestPairingList() {
    yield takeEvery(actionTypes.FETCH_LATEST_PAIRING_LIST, fetchLatestPairingList)
}

export function* fetchHumans() {
    try {
        const response = yield call(apiGet, '/humans');
        yield put(storeHumans(response.data));
    } catch (error) {}
}

export function* watchFetchHumans() {
    yield takeEvery(actionTypes.FETCH_HUMANS, fetchHumans)
}

export function* fetchNewPairs() {
    try {
        const response = yield call(apiGet, '/pair');
        yield put(storePairingListId(response.data.internalId));
        yield put(storePairingList(response.data.pairingList));
    } catch (error) {}
}

export function* watchFetchNewPairs() {
    yield takeEvery(actionTypes.FETCH_NEW_PAIRS, fetchNewPairs)
}

export function* recordPairingList(action) {
    try {
        yield call(apiPost, `/pair/record/${action.id}`);
    } catch (error) {}
}

export function* watchRecordPairingList() {
    yield takeEvery(actionTypes.RECORD_PAIRING_LIST, recordPairingList)
}

export function* addHuman(action) {
    try {
        yield call(apiPost, '/humans', {name: action.name, email: action.email});
        yield call(fetchHumans);
    } catch (error) {}
}

export function* watchAddHuman() {
    yield takeEvery(actionTypes.ADD_HUMAN, addHuman)
}

export function* deleteHuman(action) {
    try {
        yield call(apiDelete, '/humans', action.human);
        yield call(fetchHumans);
    } catch (error) {}
}

export function* watchDeleteHuman() {
    yield takeEvery(actionTypes.DELETE_HUMAN, deleteHuman)
}

export default function* rootSaga() {
    yield [
        fork(watchFetchLatestPairingList),
        fork(watchFetchHumans),
        fork(watchFetchNewPairs),
        fork(watchRecordPairingList),
        fork(watchAddHuman),
        fork(watchDeleteHuman)
    ];
}