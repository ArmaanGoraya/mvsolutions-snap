package com.mvsolutions.snap.services;

import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata;
import com.mvsolutions.snap.Change;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface VerifyService {
    @POST("/api/verify")
    Call<VerifyResponse> verify(@Body VerifyRequest request);

}
