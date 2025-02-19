package com.json.hands_on.filter_users;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilterUsersByAgeTest {

    @Test
    public void testFilterUsersOlderThan() {
        String jsonData = "[\n" +
                "  {\"name\": \"Alice\", \"age\": 24},\n" +
                "  {\"name\": \"Bob\", \"age\": 30},\n" +
                "  {\"name\": \"Charlie\", \"age\": 28},\n" +
                "  {\"name\": \"David\", \"age\": 22},\n" +
                "  {\"name\": \"Eve\", \"age\": 35}\n" +
                "]";
        JSONArray users = new JSONArray(jsonData);

        JSONArray filteredUsers = FilterUsersByAge.filterUsersOlderThan(users, 25);

        // Expected filtered users: Bob, Charlie, and Eve (total 3 records)
        assertEquals(3, filteredUsers.length());

        // Validate that each filtered record has an age > 25
        for (int i = 0; i < filteredUsers.length(); i++) {
            JSONObject user = filteredUsers.getJSONObject(i);
            assertTrue(user.getInt("age") > 25);
        }

        // Optionally, verify that the expected names exist in the filtered result
        boolean bobFound = false, charlieFound = false, eveFound = false;
        for (int i = 0; i < filteredUsers.length(); i++) {
            JSONObject user = filteredUsers.getJSONObject(i);
            String name = user.getString("name");
            if ("Bob".equals(name)) bobFound = true;
            if ("Charlie".equals(name)) charlieFound = true;
            if ("Eve".equals(name)) eveFound = true;
        }
        assertTrue(bobFound && charlieFound && eveFound);
    }
}
