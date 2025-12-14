package attendance.domain.condition;

import attendance.domain.AttendanceCondition;
import attendance.domain.AttendanceState;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class WeekdaysCondition extends AttendanceCondition {
    private static final LocalTime START_TIME = LocalTime.of(10, 0);
    private static final LocalTime END_TIME = LocalTime.of(18, 0);

    @Override
    public AttendanceState getStateBy(LocalDateTime time) {
        LocalTime localTime = getLocalTimeOf(time);

        return getAttendanceState(localTime, START_TIME);
    }
}
