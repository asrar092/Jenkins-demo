# Time Tracker — Java Maven

A small Spring Boot web application for recording work sessions. It includes a dashboard, the greeting **“Good Night Students!!!”**, start/stop controls, session duration, and a list of previous sessions.

## Requirements
- Java 17 or later
- Maven 3.6+ (or use your IDE's Maven support)

## Run on Windows PowerShell
1. Extract `time-tracker.zip`.
2. Open PowerShell in the extracted `time-tracker` folder (the folder containing `pom.xml`).
3. Run:
   ```powershell
   mvn spring-boot:run
   ```
4. Open http://localhost:8080 in your browser.

If Maven is not installed, open the folder in IntelliJ IDEA or Eclipse as an existing Maven project and run `TimeTrackerApplication.java`.

## How to use
1. Enter a task name, such as `Java assignment`.
2. Select **Start Work**.
3. Select **Stop** when finished.
4. Review each session's start time, end time, and duration.

## API
- `GET /api/sessions` — list sessions
- `POST /api/start` — start a session; JSON body: `{"task":"Java assignment"}`
- `POST /api/stop/{id}` — stop a running session

## Important note
This starter version stores sessions in memory. Data is cleared when the application restarts. Add a database such as H2 or MySQL for persistent storage before using it for real workplace records.
