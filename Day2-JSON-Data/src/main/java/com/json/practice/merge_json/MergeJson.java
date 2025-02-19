package com.json.practice.merge_json;

import org.json.JSONObject;

public class MergeJson {
    public static void main(String[] args) {
        // Create first JSON object
        JSONObject json1 = new JSONObject();
        json1.put("name", "Alice");
        json1.put("age", 25);

        // Create second JSON object
        JSONObject json2 = new JSONObject();
        json2.put("email", "alice@example.com");
        json2.put("city", "New York");

        // Merge json2 into json1
        JSONObject mergedJson = merge(json1, json2);

        // Print the merged JSON object (pretty print)
        System.out.println(mergedJson.toString(4));
    }

    /**
     * Merges two JSONObjects. In case of duplicate keys, the value from json2 overrides the value from json1.
     *
     * @param json1 First JSONObject
     * @param json2 Second JSONObject
     * @return Merged JSONObject
     */
    public static JSONObject merge(JSONObject json1, JSONObject json2) {
        JSONObject merged = new JSONObject();

        // Add all key/value pairs from json1
        for (String key : json1.keySet()) {
            merged.put(key, json1.get(key));
        }

        // Add key/value pairs from json2 (overwriting if key already exists)
        for (String key : json2.keySet()) {
            merged.put(key, json2.get(key));
        }

        return merged;
    }
}
