package com.example.retropostdeneme;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostServiceSepetOnay {
    String API_ROUTE = "sepetOnay";
    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<PostsSepetOnay> sendPosts3(@Body PostsSepetOnay postsSepetOnay);
}
