package com.example.countriespenkov;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET; //1

public interface JsonPlaceHolderApi {
    @GET("all") //2
    Call<List<API>> getPosts();//3
}
