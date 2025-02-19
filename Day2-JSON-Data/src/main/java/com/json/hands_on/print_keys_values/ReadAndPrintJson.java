package com.json.hands_on.print_keys_values;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class ReadAndPrintJson {
    public static void main(String[] args) {
        // Path to the JSON file (ensure the file exists in the project directory)
        String filePath = "data.json";

        try {
            // Read the JSON file content
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Parse the JSON content
            JSONObject jsonObject = new JSONObject(content);

            // Iterate over all keys and print keys with their corresponding values
            Iterator<String> keys = jsonObject.keys();
            while(keys.hasNext()){
                String key = keys.next();
                Object value = jsonObject.get(key);
                System.out.println(key + " : " + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
