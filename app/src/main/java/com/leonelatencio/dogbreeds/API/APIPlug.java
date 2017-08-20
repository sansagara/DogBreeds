package com.leonelatencio.dogbreeds.API;

import com.leonelatencio.dogbreeds.Model.Dog;
import com.leonelatencio.dogbreeds.Model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Endpoint Definitions
 */

public interface APIPlug {

    /*
    These methods defines our API endpoints.
    All REST methods such as GET,POST,UPDATE,DELETE can be stated in here.
    */


	@GET("breeds/list")
    Call<Response> getDogs(
    );

    @GET("breed/{breed}/images")
    Call<Response> getImage(
        @Path("breed") String breed
    );

}