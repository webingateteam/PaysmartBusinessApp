
package com.webingate.paysmartbusinessapp.utils;
/*
import com.webingate.paysmartbusinessapp.driverapplication.Deo.AcceptRejectBookingResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.ChangepwdResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.CustomerAccountResponce;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DefaultResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDetailsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverForgotpasswordResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverLoginResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverPasswordVerificationResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverRateTheRideResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverValidateCredentialsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.EndtripResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetCustomerAccountResponce;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetdriverTripsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.LoginDriverResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.PendingDocsResponce;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.RegisterDriverResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.RideDetailsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.SaveSOSNumberResponce;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.SaveVehicleDocResponce;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.StartTripResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.TrackvehicleResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.VehicleDetailsResponse;


public interface APIInterface  {

    @POST("/api/Driverlogin/ValidateDriverCredentials")
    public Observable<List<DriverValidateCredentialsResponse>> ValidateDriver(@Body JsonObject jsonObject);

    @POST("/api/RegisterDriver/RegisterDrivers")
    public Observable<List<RegisterDriverResponse>> RegisterDriver(@Body JsonObject jsonObject);


    //responce 1
    @POST("/api/DriverMaster/SaveDriverDocuments")
    public Observable<List<DefaultResponse>> SaveDriverDocuments(@Body JsonObject jsonObject);


    @POST("/api/Driverlogin/Driverlogin")
    public Observable<List<DriverLoginResponse>> DriverLogin(@Body JsonObject jsonObject);

    @POST("/api/VehicleMaster/TrackVehicle")
    public Observable<List<TrackvehicleResponse>> TrackVehicle(@Body JsonObject jsonObject);

    @POST("/api/VehicleBooking/AcceptRejectBooking")
    public Observable<List<AcceptRejectBookingResponse>> AcceptRejectBooking(@Body JsonObject jsonObject);

    @POST("/api/VehicleBooking/DriverRatingToRide")
    public Observable<List<DriverRateTheRideResponse>> DriverRatingToRide(@Body JsonObject jsonObject);

    @GET("/api/Driverlogin/Getdrivertrips")
    public Observable<List<GetdriverTripsResponse>> Getdrivertrips(@Query("DriverNo") String driverNo);

    @GET("/api/DriverMaster/GetDriverDetails")
    public Observable<DriverDetailsResponse> GetDriverDetails(@Query("DID") String did);

    @GET("/api/VehicleMaster/GetVehicleDetails")
    public Observable<VehicleDetailsResponse> GetVehicleDetails(@Query("VID") String vid);

    @GET("/api/Common/PendingDocs")
    public Observable<PendingDocsResponce> PendingDocs(@Query("userId") String userId);// w

    @GET("/api/CustomerAccountDetails/GetCustomerAccountResponce")
    public Observable<List<GetCustomerAccountResponce>> GetCustomerAccount(@Query("userId") String userId);//w

    @POST("/api/CustomerAccountDetails/CustomerAccount")
    public Observable<List<DefaultResponse>> CustomerAccount(@Body JsonObject jsonObject);//w

    @POST("/api/VehicleBooking/StartTrip")
    public Observable<List<StartTripResponse>> StartTrip(@Body JsonObject jsonObject);

    @POST("/api/VehicleBooking/ProcessPayment")
    public Observable<List<DefaultResponse>> ProcessPayment(@Body JsonObject jsonObject);

    @POST("/api/VehicleBooking/EndTrip")
    public Observable<List<EndtripResponse>> EndTrip(@Body JsonObject jsonObject);

    @POST("/api/VehicleMaster/SaveVehicleDoc")
    public Observable<List<DefaultResponse>> SaveVehicleDoc(@Body JsonObject jsonObject);

    @POST("/api/RegisterDriver/EOTPVerification")
    public Observable<List<DefaultResponse>>EOTPVerification(@Body JsonObject jsonObject);//int

    @POST("/api/RegisterDriver/MOTPVerifications")
    public Observable<List<DefaultResponse>> MOTPVerifications(@Body JsonObject jsonObject);//int



    @POST("/api/DriverChangePwd/ChangePassword")
    public Observable<List<ChangepwdResponse>> ChangePassword(@Body JsonObject jsonObject);

    @POST("/api/DriverMaster/Driver")
    public Observable<List<DrivermasterResponse>> DriverMaster(@Body JsonObject jsonObject);

    @POST("/api/VehicleBooking/RideDetails")
    public Observable<List<RideDetailsResponse>> RideDetails(@Body JsonObject jsonObject);

    @POST("/api/DriverForgotpassword/Forgotpassword")
    public Observable<List<DriverForgotpasswordResponse>> Forgotpassword(@Body JsonObject jsonObject);

    @POST("api/RegisterDriver/Passwordverification")
    public Observable<List<DriverPasswordVerificationResponse>> Passwordverification(@Body JsonObject jsonObject);

    @POST("/api/SaveSOSNumber")
    public Observable<List<SaveSOSNumberResponce>> SaveSOSNumber(@Body JsonObject jsonObject);//w














    @POST("gallery/uploadImage")
    public Observable<SuccessMsg> UpdateImage(@Body RequestBody jsonObject);

    @GET("gallery/imagesList")
    public Observable<List<ImagesList>> GetImagesList();

    @POST("gallery/tagsList")
    public Observable<List<SuggestionsList>>  GetSuggestions(@Body JsonObject jsonObject);

    @POST("gallery/imagesearchbyTag")
    public Observable<List<ImagesList>> GetSortedImages(@Body JsonObject jsonObject);


    @POST("gallery/deleteImages")
    public Observable<SuccessMsg> DeleteImages(@Body JsonObject jsonObject);




}
*/