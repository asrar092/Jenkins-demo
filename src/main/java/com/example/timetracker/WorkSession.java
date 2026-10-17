package com.example.timetracker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WorkSession {
    private final long id;
    private final String task;
    private final LocalDateTime startTime;
    private LocalDateTime endTime;

    public WorkSession(long id, String task, LocalDateTime startTime) {
        this.id = id;
        this.task = task;
        this.startTime = startTime;
    }
    public long getId() { return id; }
    public String getTask() { return task; }
    public LocalDateTime getStartTime() { return startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public boolean isRunning() { return endTime == null; }
    public void stop() { if (endTime == null) endTime = LocalDateTime.now(); }
    public long getDurationSeconds() {
        return Math.max(0, Duration.between(startTime, endTime == null ? LocalDateTime.now() : endTime).getSeconds());
    }
    public String getStartDisplay() { return startTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")); }
    public String getEndDisplay() { return endTime == null ? "In progress" : endTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")); }
}
