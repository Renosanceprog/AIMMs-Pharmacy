package model;

public abstract class User {

    private final String userID;
    private final String username;
    private final String password;
    private String fullName;

    protected User(
            String userID,
            String username,
            String password,
            String fullName) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public abstract UserRole getRole();
}