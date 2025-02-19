package com.json.practice.car_json;

import org.json.JSONObject;

public class CarJson {
    public static void main(String[] args) {
        // Create a Car object
        Car car = new Car("Toyota", "Camry", 2020);

        // Convert the Car object to JSON using JSONObject's bean constructor
        JSONObject carJson = new JSONObject(car);

        // Print the JSON representation with pretty printing (4-space indentation)
        System.out.println(carJson.toString(4));
    }
}
