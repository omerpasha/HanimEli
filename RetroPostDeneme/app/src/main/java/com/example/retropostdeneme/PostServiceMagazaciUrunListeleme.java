package com.example.retropostdeneme;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
public interface PostServiceMagazaciUrunListeleme {

    String API_ROUTE = "magazaciUrunListeleme";
    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<PostsMagazaciUrunListeleme> sendPosts(@Body PostsMagazaciUrunListeleme postsMagazaciUrunListeleme);


}
