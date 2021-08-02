package com.example.retropostdeneme;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostServiceLogin {
    String API_ROUTE = "login";
    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
   Call<PostsLogin> sendPosts(@Body PostsLogin Posts);
}
