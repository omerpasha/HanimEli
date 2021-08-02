package com.example.retropostdeneme;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostServiceSepettenSil {
    String API_ROUTE = "sepettenSil";
    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<PostsSepettenSil> sendPosts2(@Body PostsSepettenSil PostsSepettenSil);
}
