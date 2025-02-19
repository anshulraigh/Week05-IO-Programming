package com.json.hands_on.convert_list_to_json_array;

import org.json.JSONObject;

// Java Bean representing a Person
class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Convert this Person object into a JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        return json;
    }
}