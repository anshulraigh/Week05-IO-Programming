package com.json.practice.filter_json;

import org.json.JSONArray;
import org.json.JSONObject;

public class FilterJson {
    public static void main(String[] args) {
        // Sample JSON array containing multiple records
        String jsonData = "[\n" +
                "  {\"name\": \"Alice\", \"age\": 24},\n" +
                "  {\"name\": \"Bob\", \"age\": 30},\n" +
                "  {\"name\": \"Charlie\", \"age\": 28},\n" +
                "  {\"name\": \"David\", \"age\": 22}\n" +
                "]";

        // Parse the JSON array
        JSONArray jsonArray = new JSONArray(jsonData);
        JSONArray filteredArray = filterByAge(jsonArray, 25);

        // Print the filtered records (pretty print)
        System.out.println(filteredArray.toString(4));
    }

    /**
     * Filters the given JSONArray and returns a new JSONArray with objects where the age is greater than the specified threshold.
     *
     * @param array The JSONArray to filter.
     * @param ageThreshold The age threshold to compare against.
     * @return A new JSONArray containing only records with age greater than the threshold.
     */
    public static JSONArray filterByAge(JSONArray array, int ageThreshold) {
        JSONArray result = new JSONArray();
        for (int i = 0; i < array.length(); i++) {
            JSONObject record = array.getJSONObject(i);
            if (record.has("age") && record.getInt("age") > ageThreshold) {
                result.put(record);
            }
        }
        return result;
    }
}
