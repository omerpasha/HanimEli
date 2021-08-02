package com.example.retropostdeneme;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
public interface PostServiceMusteriSiparisler {
    String API_ROUTE = "musteriSiparisler";
    @Headers({

            "Content-type: application/json"

    })
    @POST(API_ROUTE)
    Call<PostsMusteriSiparisler> sendPosts(@Body PostsMusteriSiparisler postsMusteriSiparisler);

}
