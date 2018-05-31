package com.example.guilh.restaurantmenu;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<RestaurantMenuItems>> {

    ExpandableListView restaurantsExpandableListView;
    ArrayList<String> restaurantsArrayList = new ArrayList<>();
    ArrayList<RestaurantMenuItems> restaurantMenuItemsArrayList = new ArrayList<>();
    RestaurantMenuAdapter adapter;

    List<String> listGroups = new ArrayList<>();
    Map<String, List<RestaurantMenuItems>> listItemsGroup = new HashMap<>();
    private int amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        restaurantsExpandableListView = (ExpandableListView) findViewById(R.id.restaurantsList);

        RestaurantApiInterface apiInterface = ApiClient.getClient().create(RestaurantApiInterface.class);
        Call<RestaurantsList> call = apiInterface.getRestaurantsList();

        call.enqueue(new Callback<RestaurantsList>() {
            @Override
            public void onResponse(Call<RestaurantsList> call, Response<RestaurantsList> response) {
                if (response.isSuccessful()) {
                    RestaurantsList restaurantsList = response.body();

                    for (int i = 0; i < restaurantsList.getRestaurants().size(); i++) {
                        final Restaurants restaurants = restaurantsList.getRestaurants().get(i);
                        amount = restaurantsList.getRestaurants().size();
                        listGroups.add(restaurants.getName());
                        for (int j = 1; j <= amount; j++) {

                            LoaderManager loaderManager = getSupportLoaderManager();
                            loaderManager.initLoader(j, null, MainActivity.this);
                        }


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

    @NonNull
    @Override
    public Loader<List<RestaurantMenuItems>> onCreateLoader(int id, @Nullable Bundle args) {
        return new RestaurantMenuLoader(this, amount, id);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<RestaurantMenuItems>> loader, List<RestaurantMenuItems> data) {


        listItemsGroup.put(listGroups.get(loader.getId() - 1), data);


        adapter = new RestaurantMenuAdapter(this, listGroups, listItemsGroup);
        restaurantsExpandableListView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<RestaurantMenuItems>> loader) {

    }
}
