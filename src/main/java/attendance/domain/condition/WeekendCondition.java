package attendance.domain.condition;

import attendance.domain.AttendanceCondition;
import attendance.domain.AttendanceState;
import attendance.util.Converter;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class WeekendCondition extends AttendanceCondition {
    @Override
    public AttendanceState getStateBy(LocalDateTime time) {
        int month = time.getMonthValue();
        int day = time.getDayOfMonth();
        DayOfWeek dayOfWeek = time.getDayOfWeek();

        String dayOfWeekend = Converter.convertDayOfWeekToString(dayOfWeek);
        throw new IllegalArgumentException(String.format("%d월 %d일 %s은 등교일이 아닙니다.", month, day, dayOfWeekend));
    }
}
