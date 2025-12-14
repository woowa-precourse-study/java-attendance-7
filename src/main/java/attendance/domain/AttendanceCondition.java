package attendance.domain;

import java.time.LocalDateTime;
import java.time.LocalTime;

public abstract class AttendanceCondition {
    private static final int LATE_LIMIT = 5;
    private static final int ABSENT_LIMIT = 30;

    public abstract AttendanceState getStateBy(LocalDateTime time);

    protected LocalTime getLocalTimeOf(LocalDateTime time) {
        int hour = time.getHour();
        int minute = time.getMinute();

        return LocalTime.of(hour, minute);
    }

    protected AttendanceState getAttendanceState(LocalTime localTime, LocalTime startTime) {
        if (localTime.isBefore(startTime.plusMinutes(LATE_LIMIT))) {
            return AttendanceState.ATTENDANCE;
        }

        if (localTime.isAfter(startTime.plusMinutes(ABSENT_LIMIT))) {
            return AttendanceState.ABSENT;
        }

        return AttendanceState.LATE;
    }
}
