package com.example.retropostdeneme;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface PostServiceMagazaciListedenUrunSil {
    String API_ROUTE = "magazaciUrunListelemeSil";
    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<PostsMagazaciListedenUrunSil> sendPosts2(@Body PostsMagazaciListedenUrunSil PostsMagazaciListedenUrunSil);
}
