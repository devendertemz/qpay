package com.quickpay.qpay.api;


import com.quickpay.qpay.models.request.Register;
import com.quickpay.qpay.models.request.ScannerCredential;
import com.quickpay.qpay.models.request.UserCredential;
import com.quickpay.qpay.models.response.AvlBalance;
import com.quickpay.qpay.models.response.LoginRespBean;
import com.quickpay.qpay.models.response.ScannerRespBean;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    @POST("user-registration")
    Call<ResponseBody> createUser(@Body Register register);


    @POST("user-sign-in")
    Call<LoginRespBean> doLogin(@Body UserCredential credential);

   /* @POST("getpayment-historybyid")
    Call<List<ScannerRespBean>> doScanner(@Body ScannerCredential credential);*/
    @POST("get-user-by-id")
    Call<ScannerRespBean> doScanner(@Body ScannerCredential credential);

/*
    @POST("get-avl-amount-byuserid")
    Call<AvlBalance>getAvlBalance();*/

    @FormUrlEncoded
    @POST("get-avl-amount-byuserid")
    Call<AvlBalance> getAvlBalance(
            @Field("user_id") String user_id
    );



/*

    @POST("user/register")
    Call<ResponseBody> createUser(@Body User user);
    @POST("user/register/otp/verify")
    Call<ResponseBody> registerOtpVerify(@Body RegisterOTP otp);

    @POST("user/register/otp/resend")
    Call<ResponseBody> resendRegisterOtp(@Body Phone phone);

    @POST("user/otp")
    Call<ResponseBody> forgotPass(@Body Phone phone);

    @POST("user/otp/resend")
    Call<ResponseBody>reSendOtp(@Body Phone phone);

    @POST("user/otp/verify")
    Call<ResponseBody>verifyOtp(@Body Otp otp);

    @PUT("user/changepassword")
    Call<ResponseBody> changePassword(@Body Password password);

    @PUT("user")
    Call<ProfileUpdateResp> updateProfile(@Body ProfileEdit profile);

    @POST("event")
    Call<EventResp> getEvent(@Query("page") int page, @Query("limit") int limit, @Body EventFilterBody filterBody);

    @POST("order")
    Call<OrderResp> bookEvent(@Body EventOrderBody eventOrderBody);

    @POST("razorpay/order")
    Call<RazorOrderResp>createRazorOrder(@Body RazorOrderBody razorOrderBody);

    @GET("banner")
    Call<List<Banner>> getBanners();

    @POST("razorpay/capture")
    Call<CapturePaymentSuccessResp> razorCapturePayment(@Body RazorCaptureBody captureBody);

    @PUT("razorpay/saverazorpaydata")
    Call<PaymentDataSaveResp> razorDataSave(@Query("id") String orderId, @Body RazorSaveDataBody dataBody);

    @POST("razorpay/createrazorepayorderidforuserwallet")
    Call<WalletCreateResp> createWalletOrder(@Body WalletAmountBody amountBody);

    @PUT("razorpay/addmoneytowallet")
    Call<AddWalletDataResp> saveWalletData(@Body WalletSaveBody saveDataBody);

    @GET("wallet")
    Call<WalletResp>getWallet();

    @GET("order/all")
    Call<List<OrderHistoryResp>> getAllOrders();

    @POST("event/celebrity")
    Call<CelebrityResp> getCelebrity(@Query("page") int page, @Query("limit") int limit, @Body EventFilterBody filter);

    @GET("FAQ")
    Call<List<FAQResp>> getFAQ();

    @GET("user/video/sendotp")
    Call<ResponseBody> getVidOtp(@Query("eventId") String eventId);
    @GET("user/video/resend")
    Call<ResponseBody> resendVidOtp(@Query("eventId") String eventId);
    @GET("user/video/verify")
    Call<ResponseBody> verifyVideo(@Query("code") String code, @Query("eventId") String eventId);

    @GET("blog")
    Call<List<BlogResp>> getBlog();

    @POST("contactus")
    Call<ResponseBody> cantactUs(@Body ContactBody contactBody);

    @GET("order/iswalletseleted/sendotp")
    Call<ResponseBody> walletSelectedSendOtp(@Query("orderId") String orderId, @Query("isWalletSelected") boolean isWallet);

    @GET("order/iswalletseleted/resendotp")
    Call<ResponseBody> walletSelectedResendOtp(@Query("orderId") String orderId, @Query("isWalletSelected") boolean isWallet);

    @GET("order/iswalletseleted/verifyotp")
    Call<WalletVerifyResp> isWalletSelectedVerifyOtp(@Query("orderId") String orderId, @Query("otp") String otp);

    @GET("order/applydiscountcode")
    Call<ApplyCouponResp> applyDiscount(@Query("orderId") String orderId, @Query("code") String coupon, @Query("userId") String userId);

    @GET("order/partialpayment")
    Call<PartialPayResp> partialPayment(@Query("orderId") String orderId);
    @POST("event/searchevent")
    Call<EventResp> searchEvents(@Body SearchReqBody searchReqBody);

    @GET("user/referral")
    Call<ReferralResp> getReferralCode();

    @GET("blog/likeandlike")
    Call<ResponseBody> blogLikeDislike(@Query("blogId") String blogId, @Query("key") String key);

    @Multipart
    @PUT("user/profileimage")
    Call<ProfileImageResp> uploadPic(@Part MultipartBody.Part image);

    @GET("event/eventuserpaymentstatus")
    Call<EventPaidCheckResp> isEventPaid(@Query("eventId") String eventId, @Query("userId") String userId);

    @GET("event/comments")
    Call<CommentResp> getLiveComment(@Query("eventId") String eventId);

    @POST("event")
    Call<ResponseBody> postLiveComment(@Query("eventId") String eventId, @Body LiveUserCommentBody commentBody);
    @GET("about")
    Call<List<PrivacyResp>> getAboutUs();
    @POST("blog/searchblogs")
    Call<List<BlogResp>> blogSearch(@Body SearchReqBody searchReqBody);*/
}
