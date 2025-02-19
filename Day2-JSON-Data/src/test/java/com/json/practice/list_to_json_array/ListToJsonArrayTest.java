package com.json.practice.list_to_json_array;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ListToJsonArrayTest {
    @Test
    public void testJsonArrayConversion() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 30));
        people.add(new Person("Charlie", 22));

        JSONArray jsonArray = new JSONArray();
        for (Person person : people) {
            jsonArray.put(person.toJson());
        }

        assertEquals(3, jsonArray.length());
        assertEquals("Alice", jsonArray.getJSONObject(0).getString("name"));
        assertEquals(25, jsonArray.getJSONObject(0).getInt("age"));
        assertEquals("Bob", jsonArray.getJSONObject(1).getString("name"));
        assertEquals(30, jsonArray.getJSONObject(1).getInt("age"));
        assertEquals("Charlie", jsonArray.getJSONObject(2).getString("name"));
        assertEquals(22, jsonArray.getJSONObject(2).getInt("age"));
    }
}
