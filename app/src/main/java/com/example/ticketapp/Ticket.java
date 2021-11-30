package com.example.ticketapp;

import java.io.Serializable;

public class Ticket implements Serializable {
    private int id;
    private String movieName;
    private String clock;
    private int ticketNo;

    public Ticket(int id, String movieName, String clock, int ticketNo) {
        this.setId(id);
        this.setMovieName(movieName);
        this.setClock(clock);
        this.setTicketNo(ticketNo);
    }

    public Ticket(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(int ticketNo) {
        this.ticketNo = ticketNo;
    }
}
