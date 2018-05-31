package com.example.guilh.restaurantmenu;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestaurantApiInterface {

    @GET("restaurants/json/")
    Call<RestaurantsList> getRestaurantsList();

    @GET("restaurant/{id}/menu/json/")
    Call<RestaurantMenuItemsList> getRestaurantMenu(@Path("id") int id);
}
