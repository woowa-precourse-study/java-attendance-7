package attendance.factory;

import attendance.domain.AttendanceCondition;
import attendance.domain.condition.MondayCondition;
import attendance.domain.condition.WeekdaysCondition;
import attendance.domain.condition.WeekendCondition;
import java.time.DayOfWeek;

public class AttendanceConditionFactory {
    public static AttendanceCondition of(DayOfWeek dayOfWeek) {
        if (dayOfWeek.equals(DayOfWeek.MONDAY)) {
            return new MondayCondition();
        }

        if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            return new WeekendCondition();
        }

        return new WeekdaysCondition();
    }
}
