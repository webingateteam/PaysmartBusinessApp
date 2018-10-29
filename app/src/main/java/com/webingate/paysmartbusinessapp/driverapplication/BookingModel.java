package com.webingate.paysmartbusinessapp.driverapplication;

/**
 * Created by Seshu on 2/28/2018.
 */

public class BookingModel {
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getbNo() {
        return bNo;
    }

    public void setbNo(String bNo) {
        this.bNo = bNo;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getSourcelatitude() {
        return sourcelatitude;
    }

    public void setSourcelatitude(String sourcelatitude) {
        this.sourcelatitude = sourcelatitude;
    }

    public String getSourcelongitude() {
        return sourcelongitude;
    }

    public void setSourcelongitude(String sourcelongitude) {
        this.sourcelongitude = sourcelongitude;
    }

    public String getDestlatitude() {
        return destlatitude;
    }

    public void setDestlatitude(String destlatitude) {
        this.destlatitude = destlatitude;
    }

    public String getDestlongitude() {
        return destlongitude;
    }

    public void setDestlongitude(String destlongitude) {
        this.destlongitude = destlongitude;
    }

    public String getCustomerMobileNo() {
        return customerMobileNo;
    }

    public void setCustomerMobileNo(String customerMobileNo) {
        this.customerMobileNo = customerMobileNo;
    }

    private String bookingId ;
    private String bNo;
    private String vid;
    private String driverId;
    private String sourcelatitude;
    private String sourcelongitude;
    private String destlatitude;
    private String destlongitude;
    private String customerMobileNo;
}
