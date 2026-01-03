package attendance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AttendanceBook {
    private final List<Attendance> attendances=new ArrayList<>();
    public AttendanceBook(){}


    public void add(LocalDateTime localDateTime){
        LocalDate localDate=localDateTime.toLocalDate();
        LocalTime localTime=localDateTime.toLocalTime();
        SchoolTime schoolTime = new SchoolCalendar().schoolTimeOf(localDate);
        attendances.add(new Attendance(localDate,localTime, schoolTime));
    }


    public boolean delete(LocalDateTime localDateTime){
        return attendances.removeIf(attendance -> Objects.equals(attendance.getLocalDate(), localDateTime.toLocalDate()));
    }


    public Attendance getAttendance(LocalDateTime localDateTime){
        Optional<Attendance> attendance =
                attendances.stream()
                        .filter(a -> a.getLocalDate().equals(localDateTime.toLocalDate()))
                        .findFirst();

        return attendance.orElseThrow(
                () -> new IllegalArgumentException("해당 날짜 출석 기록 없음")
        );

    }

}
