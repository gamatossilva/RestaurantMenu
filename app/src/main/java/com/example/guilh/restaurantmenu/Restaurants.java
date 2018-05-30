package com.example.guilh.restaurantmenu;

public class Restaurants {

    private String name;
    private int id;

    public Restaurants(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
