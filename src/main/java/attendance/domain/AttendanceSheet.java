package attendance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AttendanceSheet {
    private final List<Attendance> attendances;

    public AttendanceSheet(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Attendance attendBy(String name, LocalDateTime time) {
        validateDuplicateToday(name, time);
        Attendance attendance = Attendance.of(name, time);

        attendances.add(attendance);
        return attendance;
    }

    private void validateDuplicateToday(String name, LocalDateTime time) {
        LocalDate localDate = time.toLocalDate();

        boolean isAttend = attendances.stream()
                .filter(attendance -> name.equals(attendance.getName()))
                .anyMatch(attendance -> attendance.isAttendOn(localDate));

        if (isAttend) {
            throw new IllegalArgumentException("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
        }
    }
}
