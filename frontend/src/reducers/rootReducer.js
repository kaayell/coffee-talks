import {combineReducers} from "redux";
import {routerReducer} from "react-router-redux";
import {
    STORE_PAIRING_LIST,
    STORE_HUMANS,
    STORE_PAIRING_LIST_ID
} from "../actions/types"

export function pairingList(state = [], action = {}) {
    switch (action.type) {
        case STORE_PAIRING_LIST:
            return action.pairingList;
        default:
            return state;
    }
}

export function humans(state = [], action = {}) {
    switch (action.type) {
        case STORE_HUMANS:
            return action.humans;
        default:
            return state;
    }
}

export function pairingListId(state = null, action = {}) {
    switch (action.type) {
        case STORE_PAIRING_LIST_ID:
            return action.id;
        default:
            return state;
    }
}

export default combineReducers({
    pairingList,
    pairingListId,
    humans,
    routing: routerReducer
})