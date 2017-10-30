package com.chicago.labs.domain;

import org.springframework.data.annotation.PersistenceConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class PairingList {
    private String internalId;
    private List<Pair> pairingList;
    private LocalDateTime timestamp;

    public PairingList() {
    }

    @PersistenceConstructor
    public PairingList(final String internalId, final List<Pair> pairingList, final LocalDateTime timestamp) {
        this.internalId = internalId;
        this.pairingList = pairingList;
        this.timestamp = timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<Pair> getPairingList() {
        return pairingList;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
