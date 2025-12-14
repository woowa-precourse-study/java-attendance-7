package attendance.domain;

import attendance.factory.AttendanceConditionFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Attendance {
    private final String name;
    private final LocalDateTime time;
    private final AttendanceState state;

    private Attendance(String name, LocalDateTime time, AttendanceState state) {
        this.name = name;
        this.time = time;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public AttendanceState getState() {
        return state;
    }

    public static Attendance of(String name, LocalDateTime time) {
        AttendanceCondition condition = AttendanceConditionFactory.of(time.getDayOfWeek());
        AttendanceState state = condition.getStateBy(time);

        return new Attendance(name, time, state);
    }

    public boolean isAttendOn(LocalDate localDate) {
        LocalDate date = time.toLocalDate();
        return date.equals(localDate);
    }
}
