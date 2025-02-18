package com.ioprogramming.advance.csvencryption;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

class CsvEncryptionDecryptionTest {
    private static final String ENCRYPTED_CSV_TEST_FILE = "test_encrypted_employee_data.csv";
    private static final String DECRYPTED_CSV_TEST_FILE = "test_decrypted_employee_data.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Encrypt and create a test file
        CsvEncryptionDecryption.encryptCsvData();
    }

    @AfterEach
    void tearDown() {
        new File(ENCRYPTED_CSV_TEST_FILE).delete();
        new File(DECRYPTED_CSV_TEST_FILE).delete();
    }

    @Test
    void testEncryptCsvData() throws IOException {
        CsvEncryptionDecryption.encryptCsvData();

        assertTrue(Files.exists(Paths.get(ENCRYPTED_CSV_TEST_FILE)));

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(ENCRYPTED_CSV_TEST_FILE))) {
            String header = reader.readLine();
            assertEquals("ID,Name,Email,Salary", header);
        }
    }

    @Test
    void testDecryptCsvData() throws IOException {
        CsvEncryptionDecryption.decryptCsvData();

        assertTrue(Files.exists(Paths.get(DECRYPTED_CSV_TEST_FILE)));

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(DECRYPTED_CSV_TEST_FILE))) {
            String header = reader.readLine();
            String row1 = reader.readLine();
            String row2 = reader.readLine();

            assertEquals("ID,Name,Email,Salary", header);
            assertTrue(row1.contains("john.doe@example.com"));
            assertTrue(row2.contains("jane.smith@example.com"));
        }
    }
}
