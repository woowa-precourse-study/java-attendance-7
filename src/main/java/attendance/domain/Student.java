package attendance.domain;

import attendance.domain.vo.Attendance;
import attendance.dto.ResponseDto;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Student {
    private final Map<String, List<Attendance>> attendances;

    private Student(Map<String,List<Attendance>> attendances){
        this.attendances=attendances;
    }
    public static Student of(Map<String,List<Attendance>> attendances){
        return new Student(attendances);
    }

    // TODO: 나의 출석 체크
    public ResponseDto.todayAttendance checkTodayAttendance(String name, LocalDateTime localDateTime) throws ParseException {
        if (!attendances.containsKey(name)){
            throw new IllegalArgumentException("등록되지 않은 닉네임입니다.");
        }

        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date today = Date.from(instant);
        Attendance todayAttendance=Attendance.of(today);
        String result = todayAttendance.returnAttendanceResult();
        return new ResponseDto.todayAttendance(todayAttendance.wholeDate(),result);
    }



}
