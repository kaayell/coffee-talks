import {
    FETCH_LATEST_PAIRING_LIST,
    STORE_PAIRING_LIST,
    STORE_PAIRING_LIST_ID,
    FETCH_HUMANS,
    STORE_HUMANS,
    FETCH_NEW_PAIRS,
    RECORD_PAIRING_LIST,
    ADD_HUMAN
} from "./types";

export function fetchLatestPairingList() {
    return {
        type: FETCH_LATEST_PAIRING_LIST
    }
}

export function storePairingList(pairingList) {
    return {
        type: STORE_PAIRING_LIST,
        pairingList
    }
}

export function storePairingListId(id) {
    return {
        type: STORE_PAIRING_LIST_ID,
        id
    }
}

export function fetchHumans() {
    return {
        type: FETCH_HUMANS
    }
}

export function fetchNewPairs() {
    return {
        type: FETCH_NEW_PAIRS
    }
}

export function storeHumans(humans) {
    return {
        type: STORE_HUMANS,
        humans
    }
}

export function recordPairingList(id) {
    return {
        type: RECORD_PAIRING_LIST,
        id
    }
}

export function addHuman(name, email) {
    return {
        type: ADD_HUMAN,
        name,
        email
    }
}