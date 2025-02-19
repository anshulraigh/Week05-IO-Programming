package com.json.hands_on.convert_list_to_json_array;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class ListToJsonArray {

    public static void main(String[] args) {
        // Create a list of Person objects
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 30));
        people.add(new Person("Charlie", 22));

        // Convert the list of Person objects into a JSON array
        JSONArray jsonArray = new JSONArray();
        for (Person person : people) {
            // Convert each Person object to JSONObject and add to JSONArray
            jsonArray.put(person.toJson());
        }

        // Print the resulting JSON array with pretty printing
        System.out.println(jsonArray.toString(2));
    }
}


