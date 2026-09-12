package model;

import java.time.LocalDate;

public class Prescription {

    private final String prescriptionID;
    private final String customerID;
    private final String doctorID;
    private final PrescriptionStatus status;
    private final LocalDate dateRequested;
    private final LocalDate dateApproved;
    private final LocalDate validUntil;

    public Prescription(
            String prescriptionID,
            String customerID,
            String doctorID,
            PrescriptionStatus status,
            LocalDate dateRequested,
            LocalDate dateApproved,
            LocalDate validUntil) {
        this.prescriptionID = prescriptionID;
        this.customerID = customerID;
        this.doctorID = doctorID;
        this.status = status;
        this.dateRequested = dateRequested;
        this.dateApproved = dateApproved;
        this.validUntil = validUntil;
    }

    public String getPrescriptionID() {
        return prescriptionID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public PrescriptionStatus getStatus() {
        return status;
    }

    public LocalDate getDateRequested() {
        return dateRequested;
    }

    public LocalDate getDateApproved() {
        return dateApproved;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public boolean isValid() {
        return status == PrescriptionStatus.APPROVED
                && validUntil != null
                && !validUntil.isBefore(LocalDate.now());
    }
}