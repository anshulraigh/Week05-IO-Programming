package com.json.practice.validate_json;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidateJsonTest {

    @Test
    public void testValidJson() {
        String validJson = "{ \"name\": \"John Doe\", \"email\": \"john@example.com\", \"age\": 30 }";
        assertTrue(ValidateJson.validateJsonStructure(validJson));
    }

    @Test
    public void testInvalidJson() {
        String invalidJson = "{ \"name\": \"John Doe\", \"email\": \"john@example.com\" }"; // missing age
        assertFalse(ValidateJson.validateJsonStructure(invalidJson));
    }
}
