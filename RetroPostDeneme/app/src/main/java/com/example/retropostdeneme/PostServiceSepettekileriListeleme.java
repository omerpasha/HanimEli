package com.example.retropostdeneme;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostServiceSepettekileriListeleme {
    String API_ROUTE = "sepettekiUrunListeleme";
    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<PostsSepettekileriListeleme> sendPosts(@Body PostsSepettekileriListeleme postsSepettekileriListeleme);
}
