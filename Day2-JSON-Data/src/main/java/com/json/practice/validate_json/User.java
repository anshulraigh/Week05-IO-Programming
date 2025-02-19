package com.json.practice.validate_json;


// POJO representing the expected JSON structure
class User {
    private String name;
    private String email;
    private int age;

    // Default constructor (required for Jackson)
    public User() {}

    // Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

