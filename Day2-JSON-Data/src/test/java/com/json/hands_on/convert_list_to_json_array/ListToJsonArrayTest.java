package com.json.hands_on.convert_list_to_json_array;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ListToJsonArrayTest {

    @Test
    public void testListToJsonArrayConversion() {
        // Create a list of Person objects
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 25));
        people.add(new Person("Bob", 30));
        people.add(new Person("Charlie", 22));

        // Convert the list into a JSON array
        JSONArray jsonArray = new JSONArray();
        for (Person person : people) {
            jsonArray.put(person.toJson());
        }

        // Validate that the JSON array contains 3 objects
        assertEquals(3, jsonArray.length());

        // Validate the first object
        assertEquals("Alice", jsonArray.getJSONObject(0).getString("name"));
        assertEquals(25, jsonArray.getJSONObject(0).getInt("age"));

        // Validate the second object
        assertEquals("Bob", jsonArray.getJSONObject(1).getString("name"));
        assertEquals(30, jsonArray.getJSONObject(1).getInt("age"));

        // Validate the third object
        assertEquals("Charlie", jsonArray.getJSONObject(2).getString("name"));
        assertEquals(22, jsonArray.getJSONObject(2).getInt("age"));
    }
}
