package attendance.controller;

import attendance.domain.vo.Attendance;
import attendance.service.AttendanceService;

import java.util.List;

public class AttendanceController {
    private final InputView inputView;
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.inputView = new InputView();
        this.attendanceService=attendanceService;
    }

    public void startAttendance(){
        //[Attendance[name=쿠키, date=2024-12-13, day=금요일, time=10:08]
        List<Attendance> attendances=inputView.readAttendanceCsv();

    }


}
