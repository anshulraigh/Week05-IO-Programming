package com.ioprogramming.advance.csvencryption;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CsvEncryptionDecryption {
    private static final String CSV_FILE_PATH = "employee_data.csv";
    private static final String ENCRYPTED_CSV_FILE_PATH = "encrypted_employee_data.csv";
    private static final String DECRYPTED_CSV_FILE_PATH = "decrypted_employee_data.csv";
    private static final String SECRET_KEY = "1234567890123456"; // 16-byte secret key for AES encryption

    public static void main(String[] args) {
        encryptCsvData();
        decryptCsvData();
    }

    public static void encryptCsvData() {
        try (CSVWriter writer = new CSVWriter(new FileWriter(ENCRYPTED_CSV_FILE_PATH))) {
            // Write header
            writer.writeNext(new String[]{"ID", "Name", "Email", "Salary"});

            // Sample data
            List<String[]> data = Arrays.asList(
                    new String[]{"1", "John Doe", "john.doe@example.com", "50000"},
                    new String[]{"2", "Jane Smith", "jane.smith@example.com", "60000"}
            );

            // Encrypt sensitive fields (Email, Salary)
            for (String[] record : data) {
                record[2] = encrypt(record[2]); // Encrypt Email
                record[3] = encrypt(record[3]); // Encrypt Salary
                writer.writeNext(record);
            }

            System.out.println("CSV Data encrypted and written to: " + ENCRYPTED_CSV_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error during encryption: " + e.getMessage());
        }
    }

    public static void decryptCsvData() {
        try (CSVReader reader = new CSVReader(new FileReader(ENCRYPTED_CSV_FILE_PATH));
             CSVWriter writer = new CSVWriter(new FileWriter(DECRYPTED_CSV_FILE_PATH))) {

            List<String[]> records = reader.readAll();

            // Write header to the decrypted CSV
            writer.writeNext(records.get(0));

            // Decrypt sensitive fields
            for (int i = 1; i < records.size(); i++) {
                records.get(i)[2] = decrypt(records.get(i)[2]); // Decrypt Email
                records.get(i)[3] = decrypt(records.get(i)[3]); // Decrypt Salary
                writer.writeNext(records.get(i));
            }

            System.out.println("CSV Data decrypted and written to: " + DECRYPTED_CSV_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error during decryption: " + e.getMessage());
        }
    }

    // Method to encrypt data
    private static String encrypt(String data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedData = cipher.doFinal(data.getBytes());
            return Base64.encodeBase64String(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    // Method to decrypt data
    private static String decrypt(String encryptedData) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedData = cipher.doFinal(Base64.decodeBase64(encryptedData));
            return new String(decryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }
}
