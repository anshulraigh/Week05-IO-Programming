package com.json.practice.filter_json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilterJsonTest {

    @Test
    public void testFilterByAge() {
        String jsonData = "[\n" +
                "  {\"name\": \"Alice\", \"age\": 24},\n" +
                "  {\"name\": \"Bob\", \"age\": 30},\n" +
                "  {\"name\": \"Charlie\", \"age\": 28},\n" +
                "  {\"name\": \"David\", \"age\": 22}\n" +
                "]";

        JSONArray jsonArray = new JSONArray(jsonData);
        JSONArray filteredArray = FilterJson.filterByAge(jsonArray, 25);

        // Expected to filter Bob and Charlie as they have age > 25
        assertEquals(2, filteredArray.length());

        JSONObject firstRecord = filteredArray.getJSONObject(0);
        JSONObject secondRecord = filteredArray.getJSONObject(1);

        // Validate first record (Bob)
        assertEquals("Bob", firstRecord.getString("name"));
        assertTrue(firstRecord.getInt("age") > 25);

        // Validate second record (Charlie)
        assertEquals("Charlie", secondRecord.getString("name"));
        assertTrue(secondRecord.getInt("age") > 25);
    }
}
