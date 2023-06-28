package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class OrderRequest {
    private int clientId;
    private Date StartDate;

    public java.sql.Date getEndDate() {
        return (java.sql.Date) endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    private Date endDate;
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public java.sql.Date getStartDate() {
        return (java.sql.Date) StartDate;
    }

    public void setStartDate(Date startDate) {
        StartDate = startDate;
    }

    public OrderRequest(@JsonProperty("clientId") int clientId,@JsonProperty("OrderDate") Date startDate, @JsonProperty("DispatchDate") Date endDate) {
        this.clientId = clientId;
        this.StartDate = startDate;
        this.endDate = endDate;

    }


}
