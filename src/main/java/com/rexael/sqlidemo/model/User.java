package com.rexael.sqlidemo.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class User {

    private int id;
    private String username;
    private String password;
    private String email;
    private Timestamp createdAt;
    private boolean isActive;

    private int lessonsCompleted;
    private int practiceAttempts;
    private int securityScore;
    private Timestamp lastLogin;

    public User() {
    }

    public User(int id, String username, String password,
                String email, Timestamp createdAt, boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // Dashboard fields

    public String getInitials() {
        if (username == null || username.isBlank()) {
            return "U";
        }

        String[] parts = username.trim().split("\\s+");

        if (parts.length == 1) {
            return parts[0].substring(0, 1).toUpperCase();
        }

        return (parts[0].substring(0, 1)
                + parts[parts.length - 1].substring(0, 1))
                .toUpperCase();
    }

    public int getLessonsCompleted() {
        return lessonsCompleted;
    }

    public void setLessonsCompleted(int lessonsCompleted) {
        this.lessonsCompleted = lessonsCompleted;
    }

    public int getPracticeAttempts() {
        return practiceAttempts;
    }

    public void setPracticeAttempts(int practiceAttempts) {
        this.practiceAttempts = practiceAttempts;
    }

    public int getSecurityScore() {
        return securityScore;
    }

    public void setSecurityScore(int securityScore) {
        this.securityScore = securityScore;
    }

    public String getLastActivity() {
        if (lastLogin == null) {
            return "Not yet";
        }

        return new SimpleDateFormat("dd MMM yyyy, HH:mm")
                .format(lastLogin);
    }

    public String getRole() {
        return "Student / Learner";
    }

    public String getMemberSince() {
        if (createdAt == null) {
            return "Not available";
        }

        return new SimpleDateFormat("MMMM yyyy")
                .format(createdAt);
    }

    public String getLastLogin() {
        if (lastLogin == null) {
            return "Not available";
        }

        return new SimpleDateFormat("dd MMM yyyy, HH:mm")
                .format(lastLogin);
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
}