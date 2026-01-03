package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendancePolicy {
    public static Attendance.Status decideStatus(LocalDate date, LocalTime time, SchoolTime schoolTime){
        if (!schoolTime.isCampusTime(time)){
            throw new IllegalArgumentException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
        }

        if (time.isAfter(schoolTime.getStart().plusMinutes(30))){
            return Attendance.Status.ABSENT;
        }
        if (time.isAfter(schoolTime.getStart().plusMinutes(5))){
            return Attendance.Status.LATE;
        }
        return Attendance.Status.ATTENDANCE;
    }

}
