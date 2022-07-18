package com.memorynotfound.integration;


import javax.persistence.*;
import java.time.LocalDateTime;

//106, EUR/USD, 1.1000,1.2000,01-06-2020 12:01:01:001
@Entity
@Table(name = "PRICE")
public class Price {
    @Id
    private String id;
    private String instrumentName;
    private Double bid;
    private Double ask  ;

    private LocalDateTime timestamp;

    public Price() {}

    @Override
    public String toString() {
        return "Price{" +
                "id='" + id + '\'' +
                ", instrumentName='" + instrumentName + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", timestamp=" + timestamp +
                '}';
    }

    public Price(String id, String instrumentName, Double bid, Double ask, LocalDateTime timestamp) {
        this.id = id;
        this.instrumentName = instrumentName;
        this.bid = bid;
        this.ask = ask;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }



}
