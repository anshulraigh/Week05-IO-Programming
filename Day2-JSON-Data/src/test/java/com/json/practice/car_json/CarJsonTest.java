package com.json.practice.car_json;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarJsonTest {

    @Test
    public void testCarToJsonConversion() {
        // Create a Car object
        Car car = new Car("Toyota", "Camry", 2020);

        // Convert the Car object to JSON
        JSONObject carJson = new JSONObject(car);

        // Validate the JSON content
        assertEquals("Toyota", carJson.getString("make"));
        assertEquals("Camry", carJson.getString("model"));
        assertEquals(2020, carJson.getInt("year"));
    }
}
