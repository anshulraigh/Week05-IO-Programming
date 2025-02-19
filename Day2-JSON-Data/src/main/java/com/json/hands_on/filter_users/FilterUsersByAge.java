package com.json.hands_on.filter_users;

import org.json.JSONArray;
import org.json.JSONObject;

public class FilterUsersByAge {
    public static void main(String[] args) {
        // Sample JSON array containing user records
        String jsonData = "[\n" +
                "  {\"name\": \"Alice\", \"age\": 24},\n" +
                "  {\"name\": \"Bob\", \"age\": 30},\n" +
                "  {\"name\": \"Charlie\", \"age\": 28},\n" +
                "  {\"name\": \"David\", \"age\": 22},\n" +
                "  {\"name\": \"Eve\", \"age\": 35}\n" +
                "]";

        // Parse the JSON data into a JSONArray
        JSONArray users = new JSONArray(jsonData);

        // Filter users older than 25
        JSONArray filteredUsers = filterUsersOlderThan(users, 25);

        // Print filtered users
        System.out.println("Users older than 25:");
        for (int i = 0; i < filteredUsers.length(); i++) {
            JSONObject user = filteredUsers.getJSONObject(i);
            System.out.println(user.toString(2));
        }
    }

    /**
     * Filters the JSONArray of users and returns only those users whose age is greater than the given threshold.
     *
     * @param users The JSONArray containing user records.
     * @param ageThreshold The age threshold.
     * @return A new JSONArray containing users older than the threshold.
     */
    public static JSONArray filterUsersOlderThan(JSONArray users, int ageThreshold) {
        JSONArray result = new JSONArray();
        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            if (user.has("age") && user.getInt("age") > ageThreshold) {
                result.put(user);
            }
        }
        return result;
    }
}
