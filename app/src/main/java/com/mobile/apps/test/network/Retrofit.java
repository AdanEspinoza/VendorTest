package com.mobile.apps.test.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobile.apps.test.beans.ResultsResponse;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by snakelogan on 2/8/16.
 */
public class Retrofit {

    private static final String URL =
            "http://api.themoviedb.org/3/discover";

    public static final String path = "movie?primary_release_date.gte=2014-09-15&primary_release_date.lte=2014-10-22&api_key=64b6f3a69e5717b13ed8a56fe4417e71";
    static Gson gson = new GsonBuilder().create();

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(URL)
            .setLogLevel(RestAdapter.LogLevel.FULL.FULL)
            .setErrorHandler(ErrorHandler.DEFAULT)
            .build();

    private static final API_Interface api_interface = REST_ADAPTER.create(API_Interface.class);

    public static API_Interface getService() {
        return api_interface;
    }

    public interface API_Interface {

        @GET("/{path}")
        ResultsResponse getMovies(@Path(value = "path", encode = false) String path);
    }



}