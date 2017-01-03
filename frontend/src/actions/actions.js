import {
    FETCH_LATEST_PAIRING_LIST,
    PAIRING_LIST
} from "./types";

export function fetchLatestPairingList() {
    return {
        type: FETCH_LATEST_PAIRING_LIST
    }
}

export function pairingList(pairingList) {
    return {
        type: PAIRING_LIST,
        pairingList
    }
}