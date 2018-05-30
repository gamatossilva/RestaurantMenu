package com.example.guilh.restaurantmenu;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestaurantApiInterface {

    @GET("json/")
    Call<RestaurantsList> getRestaurantsList();
}
