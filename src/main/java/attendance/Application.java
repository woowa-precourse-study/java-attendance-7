package attendance;

import attendance.controller.AttendanceController;
import attendance.service.AttendanceService;

import java.text.ParseException;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        AttendanceService attendanceService = new AttendanceService();
        AttendanceController attendanceController = new AttendanceController(attendanceService);
        try {
            attendanceController.startAttendance();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
