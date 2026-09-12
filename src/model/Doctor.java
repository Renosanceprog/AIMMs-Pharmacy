package model;

import java.time.LocalDate;

public class Doctor extends User {

    private final StaffStatus status;
    private final LocalDate dateRegistered;
    private final LocalDate dateApproved;

    public Doctor(
            String userID,
            String username,
            String password,
            String fullName,
            StaffStatus status,
            LocalDate dateRegistered,
            LocalDate dateApproved) {
        super(userID, username, password, fullName);
        this.status = status;
        this.dateRegistered = dateRegistered;
        this.dateApproved = dateApproved;
    }

    public StaffStatus getStatus() {
        return status;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }

    public LocalDate getDateApproved() {
        return dateApproved;
    }

    @Override
    public UserRole getRole() {
        return UserRole.DOCTOR;
    }
}