import {combineReducers} from "redux";
import {routerReducer} from "react-router-redux";
import {PAIRING_LIST} from "../actions/types"

export function pairingList(state = [], action = {}) {
    switch (action.type) {
        case PAIRING_LIST:
            return action.pairingList;
        default:
            return state;
    }
}

export default combineReducers({
    pairingList,
    routing: routerReducer
})