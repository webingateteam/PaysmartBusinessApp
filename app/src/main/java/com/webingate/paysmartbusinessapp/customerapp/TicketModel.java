package com.webingate.paysmartbusinessapp.customerapp;

/**
 * Created by Seshu on 3/24/2017.
 */


public class TicketModel {


    public String getTicketNo() {
        return ticketNo;
    }

    public void setTicketNo(String ticketNo) {
        this.ticketNo = ticketNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    private String ticketNo;
    private String date;
    private String source;
    private String destination;

}