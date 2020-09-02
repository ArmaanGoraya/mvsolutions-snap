package com.mvsolutions.snap.services;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://3.233.57.250";

    public static VerifyService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(VerifyService.class);
    }
}