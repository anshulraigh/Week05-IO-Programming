package com.json.practice.merge_json;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MergeJsonTest {

    @Test
    public void testMergeJsonObjects() {
        JSONObject json1 = new JSONObject();
        json1.put("name", "Alice");
        json1.put("age", 25);

        JSONObject json2 = new JSONObject();
        json2.put("email", "alice@example.com");
        json2.put("city", "New York");

        JSONObject merged = MergeJson.merge(json1, json2);

        // Validate merged JSON object
        assertEquals("Alice", merged.getString("name"));
        assertEquals(25, merged.getInt("age"));
        assertEquals("alice@example.com", merged.getString("email"));
        assertEquals("New York", merged.getString("city"));
    }
}
