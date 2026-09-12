package model;

import java.time.LocalDate;

public class Admin extends User {

    private final AdminStatus status;
    private final LocalDate dateCreated;

    public Admin(
            String userID,
            String username,
            String password,
            String fullName,
            AdminStatus status,
            LocalDate dateCreated) {
        super(userID, username, password, fullName);
        this.status = status;
        this.dateCreated = dateCreated;
    }

    public AdminStatus getStatus() {
        return status;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    @Override
    public UserRole getRole() {
        return UserRole.ADMIN;
    }
}