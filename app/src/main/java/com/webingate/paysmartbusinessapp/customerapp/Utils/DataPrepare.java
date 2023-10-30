package com.webingate.paysmartbusinessapp.customerapp.Utils;

import android.content.Context;
import android.util.Base64;

import com.google.gson.JsonObject;
import com.webingate.paysmartbusinessapp.BuildConfig;
import com.webingate.paysmartbusinessapp.customerapp.Utils.APIInterface;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataPrepare {


    Context mContext;
    private static DataPrepare instance = null;

    public DataPrepare(Context context){
        this.mContext = context;
    }

    public static DataPrepare getdataInstance(Context context){

        if (instance == null){
            instance = new DataPrepare(context);
        }

        return instance;
    }
    public static DataPrepare get(Context context) {
        if (instance == null) {
            instance = new DataPrepare(context);

        }
        return instance;
    }
    // Preapring Multipart Body For Uploading An Image To Server
    public static MultipartBody PrepareDataForUploadingImage(String tagName, File file){
        JsonObject json = new JsonObject();
        json.addProperty("image_tags", tagName);
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        builder.addFormDataPart("content", json.toString());

        /*for(int i =0;i<send_data.get(position).getImages().size();i++){
            Log.v("images_Path",""+send_data.get(position).getImages().size());
            File file;
            if(send_data.get(position).getImages().get(i).equalsIgnoreCase("")){
                //file=getfile();
            }else{
                file = new File(send_data.get(position).getImages().get(i));*/
                builder.addFormDataPart("upload_file", file.getName(),RequestBody.create(MediaType.parse("image/*"), file));
           // }
      //  }


        return builder.build();
        // SaveData(json);
      //  SaveData(requestBody,position);
    }
    public APIInterface getrestadapter() {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder .readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                String creds = String.format("%s:%s","admin", "1234");
                                String auth = "Basic " + Base64.encodeToString(creds.getBytes(), Base64.NO_WRAP);
                                Request request = chain.request().newBuilder()
                                        .addHeader("Accept", "Application/JSON")
                                      //  .addHeader("Authorization", auth)
                                        .build();
                                return chain.proceed(request);
                            }
                        }).build();


        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        //builder.addInterceptor(new UnauthorisedInterceptor(context));
        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        return retrofit.create(APIInterface.class);
    }
}
