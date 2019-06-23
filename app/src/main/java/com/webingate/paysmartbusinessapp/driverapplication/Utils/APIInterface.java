package com.webingate.paysmartbusinessapp.driverapplication.Utils;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.AddCardResponse;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.BusinessEOTPVerificationResponse;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.BusinessappuserValidateResp;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.ConfigResponse;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.MOTPVerification;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.MOTPVerificationResponse;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.WalletBalanceResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.AcceptRejectBookingResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.ActiveCountries;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.AllocatedDriverListResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.AssignDriverResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.BusinessResendOTPResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.ChangepwdResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DefaultResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDetailsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverDocumentsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverForgotpasswordResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverLoginResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverPasswordVerificationResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverRateTheRideResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DriverValidateCredentialsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.DrivermasterResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.EndtripResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetCustomerAccountResponce;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetVehicleListResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.GetdriverTripsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.PendingDocsResponce;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.RegisterDriverResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.RideDetailsResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.SaveSOSNumberResponce;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.StartTripResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.TrackvehicleResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.UserInformationResponse;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.VehicleCreationResponce;
import com.webingate.paysmartbusinessapp.driverapplication.Deo.VehicleDetailsResponse;
import com.webingate.paysmartbusinessapp.activity.businessapp.Deo.RegisterBusinessUsers;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface APIInterface  {

    @POST("/api/Payment/SaveCardsGroup")
    public Observable<List<AddCardResponse>> SaveAddCard(@Body JsonObject jsonObject);//

    @POST("/api/UserAccount/DriverEwalletOTPSending")
    public Observable<List<MOTPVerificationResponse>> EwalletSendOTP(@Body JsonObject jsonObject);//

    @POST("/api/UserAccount/DriverEwalletOTPVerification")
    public Observable<List<MOTPVerificationResponse>> EwalletMOTPVerifications(@Body JsonObject jsonObject);

    @GET("/api/WalletBalance/DriverGetcurrentbalance")
    public Observable<List<WalletBalanceResponse>> Getcurrentbalance1(@Query("mobileno") String mobileNo);

    @GET("/api/WalletBalance/DriverGetEwalletHistory")
    public Observable<List<WalletBalanceResponse>> GetEwalletHistory(@Query("mobileno") String mobileNo);

    @POST("/api/WalletBalance/DriverWalletBalance")
    public Observable<List<WalletBalanceResponse>> WalletBalance(@Body JsonObject jsonObject);
    @POST("/api/Driverlogin/ValidateDriverCredentials")
    public Observable<List<DriverValidateCredentialsResponse>> ValidateDriver(@Body JsonObject jsonObject);

    @POST("/api/BusinessAppUser/RegisterBusinessAppUser")
    public Observable<List<RegisterBusinessUsers>> Savebusinessappusers(@Body JsonObject jsonObject);

    @POST("/api/BusinessAppUser/BusinessAppUserEOTPVerification")
    public Observable<List<BusinessEOTPVerificationResponse>> BusinessEOTPVerification(@Body JsonObject jsonObject);

    @POST("/api/BusinessAppUser/BusinessAppUserForgotpassword")
    public Observable<List<DriverForgotpasswordResponse>> Forgotpassword1(@Body JsonObject jsonObject);

    @POST("/api/BusinessAppUser/BusinessAppUserResendOTP")
    public Observable<List<BusinessResendOTPResponse>> BusinessAppResendOTP(@Body JsonObject jsonObject);


    @POST("/api/BusinessAppUser/BusinessAppUserPasswordverification")
    public Observable<List<DriverPasswordVerificationResponse>> BusinessappForgotpassword(@Body JsonObject jsonObject);

    @POST("/api/BusinessAppUser/BusinessAppUserMOTPverifications")
    public Observable<List<MOTPVerification>> MOTPVerifications1(@Body JsonObject jsonObject);

    @POST("/api/login/BusinessAppUserValidateCredentials")
    public Observable<List<BusinessappuserValidateResp>> BusinessAppUserValidateCredentials(@Body JsonObject jsonObject);

    @GET("/api/DriverMaster/Master")
    public Observable<List<DrivermasterResponse>> GetDriverList(@Query("ctryId") String ctryId);

    @GET("/api/allocatedriver/Getallocatedriver")
    public Observable<List<AllocatedDriverListResponse>> Getallocatedriver(@Query("VID") int VID);


    @GET("/api/BusinessAppUser/GetBusinessappusersusertypeid")
    public Observable<List<DrivermasterResponse>> GetDriverList_usertype(@Query("acct") String acct, @Query("uit") int uit);

    @GET("/api/BusinessAppUser/GetBusinessappusersuser")
    public Observable<List<UserInformationResponse>> GetUserInformation(@Query("acct") String acct, @Query("uit") int uit);

    @GET("/api/Driverlogin/GetdrivertripsBookingno")
    public Observable<List<GetdriverTripsResponse>> Getdrivertripsbookingno(@Query("DriverNo") String driverNo, @Query("bno") String bno);

    @GET("/api/VehicleMaster/GetVehcileList")
    public Observable<List<GetVehicleListResponse>> GetVehiclesList(@Query("ctryId") int ctryid,@Query("fid") int fid,@Query("vgId") int vgid);

    @POST("/api/RegisterDriver/RegisterDrivers")
    public Observable<List<RegisterDriverResponse>> RegisterDriver(@Body JsonObject jsonObject);

    @POST("/api/allocatedriver/AllocateDriver")
    public Observable<List<AssignDriverResponse>> AssignDriver(@Body JsonObject jsonObject);
    //responce 1
    @POST("/api/DriverMaster/SaveDriverDocuments")
    public Observable<List<DefaultResponse>> SaveDriverDocuments(@Body JsonObject jsonObject);
    @POST("/api/DriverMaster/SaveDriverDocuments")
    public Observable<List<DriverDocumentsResponse>> SaveDriverDocuments1(@Body JsonObject jsonObject);

    @POST("/api/Driverlogin/Driverlogin")
    public Observable<List<DriverLoginResponse>> DriverLogin(@Body JsonObject jsonObject);

    @POST("/api/VehicleMaster/TrackVehicle")
    public Observable<List<TrackvehicleResponse>> TrackVehicle(@Body JsonObject jsonObject);

    @POST("/api/VehicleBooking/AcceptRejectBooking")
    public Observable<List<AcceptRejectBookingResponse>> AcceptRejectBooking(@Body JsonObject jsonObject);

    @POST("/api/VehicleBooking/DriverRatingToRide")
    public Observable<List<DriverRateTheRideResponse>> DriverRatingToRide(@Body JsonObject jsonObject);

    @GET("/api/Driverlogin/Getdrivertrips")
    public Observable<List<GetdriverTripsResponse>> Getdrivertrips(@Query("DriverNo") String driverNo,@Query("status") int status);

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

    @POST("api/VehicleMaster/VehicleCreation")
    public Observable<List<VehicleCreationResponce>> VehicleCreationverification(@Body JsonObject jsonObject);

    @POST("/api/SaveSOSNumber")
    public Observable<List<SaveSOSNumberResponce>> SaveSOSNumber(@Body JsonObject jsonObject);//w

    @GET("/api/Common/GetCountry")
    public Observable<List<ActiveCountries>> GetActiveCountry(@Query("active") int active);//i

    @GET("/api/Common/GetGroups")
    public Observable<List<ConfigResponse>> GetGroups(@Query("Id") int Id);

    @POST("/api/Common/ConfigData")
    public Observable<List<ConfigResponse>> ConfigData(@Body JsonObject jsonObject);















    /*


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


    */

}
