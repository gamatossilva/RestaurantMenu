package com.example.guilh.restaurantmenu;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class RestaurantMenuLoader extends AsyncTaskLoader<List<RestaurantMenuItems>> {

    private int amount;
    List<RestaurantMenuItems> mData;
    private int id;

    public RestaurantMenuLoader(Context context, int amount, int id) {
        super(context);
        this.amount = amount;
        this.id = id;
    }

    @Override
    protected void onStartLoading() {
        if (mData != null) {
        deliverResult(mData);
        } else {
        forceLoad();
        }
    }

    @Override
    public List<RestaurantMenuItems> loadInBackground() {
        RestaurantApiInterface apiInterface = ApiClient.getClient().create(RestaurantApiInterface.class);
        List<RestaurantMenuItems> restaurantMenu = new ArrayList<>();

        Call<RestaurantMenuItemsList> call = apiInterface.getRestaurantMenu(id);
        Response<RestaurantMenuItemsList> response = null;

        try {
            response = call.execute();
            RestaurantMenuItemsList restaurantMenuItemsList;

            if (response.isSuccessful()) {
                restaurantMenuItemsList = response.body();

                restaurantMenu.addAll(restaurantMenuItemsList.getRestaurantMenu());
            } else {
                Log.e("RESTAURANT MENU", "onResponse: " + response.errorBody());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return restaurantMenu;
    }

    @Override
    public void deliverResult(List<RestaurantMenuItems> data) {
       mData = data;
       super.deliverResult(data);
    }
}
