package com.json.hands_on.merge_json_files;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class MergeJsonFiles {
    public static void main(String[] args) {
        // Define the paths for the two JSON files
        String filePath1 = "file1.json";
        String filePath2 = "file2.json";

        try {
            // Read the content of both files
            String content1 = new String(Files.readAllBytes(Paths.get(filePath1)));
            String content2 = new String(Files.readAllBytes(Paths.get(filePath2)));

            // Parse the contents into JSONObjects
            JSONObject json1 = new JSONObject(content1);
            JSONObject json2 = new JSONObject(content2);

            // Merge both JSON objects
            JSONObject mergedJson = mergeJsonObjects(json1, json2);

            // Print the merged JSON object with pretty printing
            System.out.println(mergedJson.toString(4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Merges two JSONObjects into one. In case of duplicate keys, the value from the second JSON overrides the first.
     *
     * @param json1 The first JSONObject.
     * @param json2 The second JSONObject.
     * @return A merged JSONObject containing keys from both.
     */
    public static JSONObject mergeJsonObjects(JSONObject json1, JSONObject json2) {
        JSONObject merged = new JSONObject();

        // Add keys and values from the first JSON
        Iterator<String> keys1 = json1.keys();
        while (keys1.hasNext()) {
            String key = keys1.next();
            merged.put(key, json1.get(key));
        }

        // Add keys and values from the second JSON (overriding duplicates)
        Iterator<String> keys2 = json2.keys();
        while (keys2.hasNext()) {
            String key = keys2.next();
            merged.put(key, json2.get(key));
        }

        return merged;
    }
}
