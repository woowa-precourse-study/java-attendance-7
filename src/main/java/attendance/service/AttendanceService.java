package attendance.service;

import attendance.domain.Student;
import attendance.domain.vo.Attendance;
import attendance.dto.RequestDto;
import attendance.dto.ResponseDto;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class AttendanceService {

    public Student getStudent(Map<String, List<Attendance>> attendances){
        return Student.of(attendances);
    }

    public ResponseDto.todayAttendance comfirmAttendance(Student student, RequestDto.todayAttendance request) throws ParseException {
        return student.checkTodayAttendance(request.Nickname(),request.today());

    }

}
