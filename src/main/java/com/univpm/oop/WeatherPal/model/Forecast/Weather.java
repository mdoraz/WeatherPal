package com.univpm.oop.WeatherPal.model.Forecast;

import java.util.HashMap;

public class Weather<id, Weather> {
    private int id;
    private String type;
    private String description;

    Weather (int id, String type, String description) {
        this.id = id;
        this.type = type;
        this.description = description;
    }

    HashMap<Integer, Weather> Weather = new HashMap<Integer, Weather>();

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getType(){
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}