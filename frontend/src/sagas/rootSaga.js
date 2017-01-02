import {takeEvery, fork} from 'redux-saga';
import * as actionTypes from '../actions/types'

function* fetchPairingList(){
    console.log("hi");
}

function* watchFetchPairingList() {
    yield takeEvery(actionTypes.FETCH_PAIRING_LIST, fetchPairingList)
}

export default function* rootSaga() {
    yield watchFetchPairingList()
}