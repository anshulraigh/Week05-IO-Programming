package com.json.hands_on.convert_json_to_xml;

import org.json.JSONObject;
import org.json.XML;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConvertJsonToXmlTest {

    @Test
    public void testJsonToXmlConversion() {
        // Create a sample JSON object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "John Doe");
        jsonObject.put("email", "john.doe@example.com");
        jsonObject.put("age", 30);

        // Convert JSON to XML with "user" as the root element
        String xml = XML.toString(jsonObject, "user");

        // Validate that the XML string contains the expected elements and values
        assertTrue(xml.contains("<user>"));
        assertTrue(xml.contains("<name>John Doe</name>"));
        assertTrue(xml.contains("<email>john.doe@example.com</email>"));
        assertTrue(xml.contains("<age>30</age>"));
        assertTrue(xml.contains("</user>"));
    }
}
