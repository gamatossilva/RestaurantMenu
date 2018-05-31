package com.example.guilh.restaurantmenu;

import com.google.gson.annotations.SerializedName;

public class RestaurantMenuItems {

    @SerializedName("description")
    private String itemDescription;

    @SerializedName("name")
    private String itemName;

    @SerializedName("price")
    private String itemPrice;

    public RestaurantMenuItems() {}

    public RestaurantMenuItems(String itemName, String itemDescription, String itemPrice) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }
}
