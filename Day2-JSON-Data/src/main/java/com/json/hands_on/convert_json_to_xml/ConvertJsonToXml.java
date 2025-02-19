package com.json.hands_on.convert_json_to_xml;

import org.json.JSONObject;
import org.json.XML;

public class ConvertJsonToXml {
    public static void main(String[] args) {
        // Create a sample JSON object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "John Doe");
        jsonObject.put("email", "john.doe@example.com");
        jsonObject.put("age", 30);

        // Convert JSON object to XML string with "user" as the root element
        String xml = XML.toString(jsonObject, "user");

        // Print the resulting XML
        System.out.println(xml);
    }
}
