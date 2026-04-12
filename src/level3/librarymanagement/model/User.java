package level3.librarymanagement.model;

import java.sql.Timestamp;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String phoneNo;
    private String address;
    private Timestamp createdAt;

    public User() {}

    public User(String firstName, String lastName, String email,
                String password, String role, String phoneNo, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public User(int userId, String firstName, String lastName, String email,
                String password, String role, String phoneNo, String address, Timestamp createdAt) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phoneNo = phoneNo;
        this.address = address;
        this.createdAt = createdAt;
    }

    // Getters & Setters

    public int getUserId() { return userId; }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public String getPhoneNo() { return phoneNo; }
    public String getAddress() { return address; }
    public Timestamp getCreatedAt() { return createdAt; }

    public void setUserId(int userId) { this.userId = userId; }

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }
    public void setAddress(String address) { this.address = address; }
}