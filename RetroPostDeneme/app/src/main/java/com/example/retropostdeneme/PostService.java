package com.example.retropostdeneme;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostService {
    String API_ROUTE = "register";
    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<Posts> sendPosts(@Body Posts posts);
}
