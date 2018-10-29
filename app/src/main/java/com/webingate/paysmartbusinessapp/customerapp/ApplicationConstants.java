package com.webingate.paysmartbusinessapp.customerapp;

import java.util.ArrayList;

/**
 * Created by Seshu on 4/25/2017.
 */

public class ApplicationConstants {
    public static  String estPrice="";
    public static int tripFlag=0;
    public static String mobileNo="";
    public static String id="";
    public static String username="";
    public static String password="";
    public static String email="";
    public static boolean verify_email=true;
    public static boolean isResetPasswordfirstWondow=true;
    public static String source="";
    public static String destination="";
    public static String date="";
    public static int sourceid=0;
    public static int destinationid=0;
    public static TravelModel travel;
    public static String bookingDate="";
    public static String bookingTime="";
    public static String walletBalance="";
    //public static ArrayList confirmedTrips=new ArrayList();
    public static String rating,comments;

    public static int vehicleType=0;
    public static final int HOME=0;
    public static final int TICKET_SOURCE_DESTINATION=1;
    public static final int STOPS=2;
    public static final int TRAVELS=3;
    public static final int BUSLAYOUT=4;
    public static final int TICKETS=5;
    public static final int EWALLET=6;
    public static final int FEEDBACK=7;
    public static final int PAYMENTS=8;
    public static final int PASSENGERDETAILS=9;

    //public static ArrayList<StopsModel> stopsArraylist = new ArrayList<StopsModel>();
    public static ArrayList<TravelModel> travelsArraylist = new ArrayList<TravelModel>();
    //public static ArrayList<Payment_Method_model> paymentMethods= new ArrayList<Payment_Method_model>();

    public static int FRAGMENT=0;
    public static ArrayList seatsSelected=new ArrayList();

    public static String bookingNo="";
    public static String booKingOTP="";
    public static String driverName;
    public static String driverId="";
    public static String mobNo;
    public static String registrationNo;
    public static String vModel;
    public static String driverimage;
    public static String vehicleimage;
    public static ArrayList<TransactionModel> walletTransactions;
    public static int marker;
    public static String dateofbirth="";
    public static String gender="";
    public static String paymenttype="";
    public static String profilepic="";

    //Registration Details
    /*public static String Username="";
    public static String Email="";
    public static String Mobilenumber="";
    public static String Password="";
    public static String Mobileotp="";
    public static String Emailotp="";
    public static String Passwordotp="";
    public static String StatusId="";
    public static String CreatedOn="";
    public static String Mobileotpsenton="";
    public static String emailotpsenton="";
    public static String noofattempts="";
    public static String Firstname="";
    public static String lastname="";
    public static String AuthTypeId="";
    public static String AltPhonenumber="";
    public static String Altemail="";
    public static String AccountNo="";*/

}
