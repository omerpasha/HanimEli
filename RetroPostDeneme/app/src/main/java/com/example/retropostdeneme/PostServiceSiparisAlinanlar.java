package com.example.retropostdeneme;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostServiceSiparisAlinanlar {
    String API_ROUTE = "siparisAlinanlar";
    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<PostsSiparisAlinanlar> sendPosts3(@Body PostsSiparisAlinanlar PostsSiparisAlinanlar);
}
