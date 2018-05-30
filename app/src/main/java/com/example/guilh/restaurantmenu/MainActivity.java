package com.example.guilh.restaurantmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView restaurantsListView;
    ArrayList<String> restaurantsArrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, restaurantsArrayList);
        restaurantsListView = (ListView) findViewById(R.id.restaurantsList);
        restaurantsListView.setAdapter(adapter);

        RestaurantApiInterface apiInterface = ApiClient.getClient().create(RestaurantApiInterface.class);
        Call<RestaurantsList> call = apiInterface.getRestaurantsList();

        call.enqueue(new Callback<RestaurantsList>() {
            @Override
            public void onResponse(Call<RestaurantsList> call, Response<RestaurantsList> response) {
                if (response.isSuccessful()) {
                    RestaurantsList restaurantsList = response.body();

                    for (int i = 0; i < restaurantsList.getRestaurants().size(); i++) {
                        Restaurants restaurants = restaurantsList.getRestaurants().get(i);

                        restaurantsArrayList.add(restaurants.getId() + ". " + restaurants.getName());
                        adapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RestaurantsList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error onFeilure: " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
