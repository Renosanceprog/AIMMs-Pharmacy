package model;

import java.time.LocalDateTime;

public class PrescriptionLog {

    private final String logID;
    private final String prescriptionID;
    private final String actorID;
    private final String actorRole;
    private final PrescriptionLogAction action;
    private final LocalDateTime dateTime;
    private final String details;

    public PrescriptionLog(
            String logID,
            String prescriptionID,
            String actorID,
            String actorRole,
            PrescriptionLogAction action,
            LocalDateTime dateTime,
            String details) {
        this.logID = logID;
        this.prescriptionID = prescriptionID;
        this.actorID = actorID;
        this.actorRole = actorRole;
        this.action = action;
        this.dateTime = dateTime;
        this.details = details;
    }

    public String getLogID() {
        return logID;
    }

    public String getPrescriptionID() {
        return prescriptionID;
    }

    public String getActorID() {
        return actorID;
    }

    public String getActorRole() {
        return actorRole;
    }

    public PrescriptionLogAction getAction() {
        return action;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDetails() {
        return details;
    }
}