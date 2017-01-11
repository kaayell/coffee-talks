package com.chicago.labs.domain;

import org.springframework.data.annotation.PersistenceConstructor;

import java.util.Date;
import java.util.List;

public class PairingList {
    private String internalId;
    private List<Pair> pairingList;
    private Date timestamp;
    private Boolean recorded;

    public PairingList() {
    }

    @PersistenceConstructor
    public PairingList(final String internalId, final List<Pair> pairingList, final Date timestamp, final Boolean recorded) {
        this.internalId = internalId;
        this.pairingList = pairingList;
        this.timestamp = timestamp;
        this.recorded = recorded;
    }

    public void setRecorded(final Boolean recorded) {
        this.recorded = recorded;
    }

    public String getInternalId() {
        return internalId;
    }

    public List<Pair> getPairingList() {
        return pairingList;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Boolean getRecorded() {
        return recorded;
    }
}
