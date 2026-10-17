package com.example.timetracker;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
public class TimeTrackerController {
    private final List<WorkSession> sessions = new CopyOnWriteArrayList<>();
    private final AtomicLong ids = new AtomicLong(1);

    @GetMapping("/sessions")
    public List<WorkSession> sessions() {
        return sessions.stream().sorted(Comparator.comparing(WorkSession::getStartTime).reversed()).toList();
    }

    @PostMapping("/start")
    public WorkSession start(@RequestBody StartRequest request) {
        boolean alreadyRunning = sessions.stream().anyMatch(WorkSession::isRunning);
        if (alreadyRunning) throw new ResponseStatusException(HttpStatus.CONFLICT, "Stop the current session before starting another.");
        String task = request.task() == null ? "" : request.task().trim();
        if (task.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please enter a task name.");
        WorkSession session = new WorkSession(ids.getAndIncrement(), task, LocalDateTime.now());
        sessions.add(session);
        return session;
    }

    @PostMapping("/stop/{id}")
    public WorkSession stop(@PathVariable long id) {
        WorkSession session = sessions.stream().filter(s -> s.getId() == id).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session not found."));
        if (!session.isRunning()) throw new ResponseStatusException(HttpStatus.CONFLICT, "This session has already stopped.");
        session.stop();
        return session;
    }

    public record StartRequest(String task) {}
}
