package attendance.controller;

import attendance.domain.AttendanceSheet;

public interface Command {
    void execute(AttendanceSheet sheet);
}
