package com.chicago.labs.domain;

import org.springframework.data.annotation.PersistenceConstructor;

import java.util.Date;

public class PairHistory {
    private String internalId;
    private String emailOne;
    private String emailTwo;
    private Integer timesPaired;
    private Date lastPairDate;

    public PairHistory() {
    }

    @PersistenceConstructor
    public PairHistory(final String internalId, final String emailOne, final String emailTwo, final Integer timesPaired, final Date lastPairDate) {
        this.internalId = internalId;
        this.emailOne = emailOne;
        this.emailTwo = emailTwo;
        this.timesPaired = timesPaired;
        this.lastPairDate = lastPairDate;
    }

    public void setInternalId(final String internalId) {
        this.internalId = internalId;
    }

    public void setEmailOne(final String emailOne) {
        this.emailOne = emailOne;
    }

    public void setEmailTwo(final String emailTwo) {
        this.emailTwo = emailTwo;
    }

    public void setTimesPaired(final Integer timesPaired) {
        this.timesPaired = timesPaired;
    }

    public void setLastPairDate(final Date lastPairDate) {
        this.lastPairDate = lastPairDate;
    }

    public String getInternalId() {
        return internalId;
    }

    public String getEmailOne() {
        return emailOne;
    }

    public String getEmailTwo() {
        return emailTwo;
    }

    public Integer getTimesPaired() {
        return timesPaired;
    }

    public Date getLastPairDate() {
        return lastPairDate;
    }
}
