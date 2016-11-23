package com.negociandola.cuponera.RestAPI.adapter;

import com.negociandola.cuponera.RestAPI.ConstantesRestAPI;
import com.negociandola.cuponera.RestAPI.Endpoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ignacio on 10/07/16.
 */
public class RestApiAdapter {
    public Endpoints establecerConexionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;
        return retrofit.create(Endpoints.class);

    }
}
