package model.log;

import java.time.LocalDateTime;

public class BatchLog {

    private final String logID;
    private final String batchID;
    private final String itemID;
    private final String actorID;
    private final String actorRole;
    private final BatchLogAction action;
    private final LocalDateTime dateTime;
    private final String details;

    public BatchLog(
            String logID,
            String batchID,
            String itemID,
            String actorID,
            String actorRole,
            BatchLogAction action,
            LocalDateTime dateTime,
            String details) {
        this.logID = logID;
        this.batchID = batchID;
        this.itemID = itemID;
        this.actorID = actorID;
        this.actorRole = actorRole;
        this.action = action;
        this.dateTime = dateTime;
        this.details = details;
    }

    public String getLogID() {
        return logID;
    }

    public String getBatchID() {
        return batchID;
    }

    public String getItemID() {
        return itemID;
    }

    public String getActorID() {
        return actorID;
    }

    public String getActorRole() {
        return actorRole;
    }

    public BatchLogAction getAction() {
        return action;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDetails() {
        return details;
    }
}