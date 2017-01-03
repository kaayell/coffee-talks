import {takeEvery} from 'redux-saga';
import { call, put } from 'redux-saga/effects'
import * as actionTypes from '../actions/types'
import {apiGet} from "../api/api"
import {pairingList} from '../actions/actions'

export function* fetchLatestPairingList() {
    try {
        const response = yield call(apiGet, '/pair/latest');
        yield put(pairingList(response.data.pairingList));
    } catch (error) {}
}

export function* watchFetchLatestPairingList() {
    yield takeEvery(actionTypes.FETCH_LATEST_PAIRING_LIST, fetchLatestPairingList)
}

export default function* rootSaga() {
    yield watchFetchLatestPairingList()
}