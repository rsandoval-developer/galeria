package com.negociandola.cuponera.RestAPI;

import com.negociandola.cuponera.RestAPI.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ignacio on 10/07/16.
 */
public interface Endpoints {
    @FormUrlEncoded
    @POST(ConstantesRestAPI.KEY_ID_POST_TOKEN)
    Call<UserResponse> registrarTokenID(@Field("token") String token);
}
