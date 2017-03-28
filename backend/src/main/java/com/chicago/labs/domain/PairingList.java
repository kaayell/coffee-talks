package com.chicago.labs.domain;

import org.springframework.data.annotation.PersistenceConstructor;

import java.time.LocalDate;
import java.util.List;

public class PairingList {
    private String internalId;
    private List<Pair> pairingList;
    private LocalDate timestamp;
    private Boolean recorded;

    public PairingList() {
    }

    @PersistenceConstructor
    public PairingList(final String internalId, final List<Pair> pairingList, final LocalDate timestamp, final Boolean recorded) {
        this.internalId = internalId;
        this.pairingList = pairingList;
        this.timestamp = timestamp;
        this.recorded = recorded;
    }

    public void setRecorded(final Boolean recorded) {
        this.recorded = recorded;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public List<Pair> getPairingList() {
        return pairingList;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public Boolean getRecorded() {
        return recorded;
    }
}
