package com.example.retropostdeneme;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostServiceSepeteEkleme {
    String API_ROUTE = "sepeteUrunEkleme";
    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<PostsSepeteEkleme> sendPosts2(@Body PostsSepeteEkleme postsSepeteEkleme);
}
