package com.json.practice.list_to_json_array;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("age", age);
        return jsonObject;
    }
}

public class ListToJsonArray {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 30));
        people.add(new Person("Charlie", 22));

        JSONArray jsonArray = new JSONArray();
        for (Person person : people) {
            jsonArray.put(person.toJson());
        }

        System.out.println("JSON Array: " + jsonArray.toString(2));
    }
}
