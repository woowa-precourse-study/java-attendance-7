package attendance.controller;

import attendance.domain.Student;
import attendance.domain.vo.Attendance;
import attendance.dto.RequestDto;
import attendance.service.AttendanceService;
import camp.nextstep.edu.missionutils.DateTimes;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class AttendanceController {
    private final InputView inputView;
    private final OutputView outputView;
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.attendanceService=attendanceService;
    }

    public void startAttendance() throws ParseException {
        Map<String,List<Attendance>> attendances=inputView.readAttendanceCsv();

        // 학생 등록
        Student students=attendanceService.getStudent(attendances);

        LocalDateTime localDateTime=DateTimes.now();
        int num=inputView.readSelectedFunction(localDateTime);
        chooseFunction(students,num,localDateTime);

    }

    public void chooseFunction(Student students,int num,LocalDateTime now) throws ParseException {
        if (num==1){
            RequestDto.todayAttendance dto=new RequestDto.todayAttendance(inputView.readNickname(),now);
            outputView.printTodayAttendance(attendanceService.comfirmAttendance(students,dto));
        }
    }


}
