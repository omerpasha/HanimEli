package com.example.retropostdeneme;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
public interface PostServiceMagazaciProfil {

    String API_ROUTE = "magazaciProfil";
    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<PostsMagazaciProfil> sendPosts(@Body PostsMagazaciProfil postsMagazaciProfil);


}
