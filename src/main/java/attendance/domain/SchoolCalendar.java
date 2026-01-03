package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

public class SchoolCalendar {
    private static Set<LocalDate> holidays=Set.of(LocalDate.of(2024,12,25));

    public SchoolTime schoolTimeOf(LocalDate localDate){
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("M월 d일 E요일", Locale.KOREAN);
        if (holidays.contains(localDate)){
            throw new IllegalArgumentException(String.format("[ERROR] %s은 등교일이 아닙니다.",localDate.format(f1)));
        }

        if (dayOfWeek==DayOfWeek.SATURDAY || dayOfWeek==DayOfWeek.SUNDAY){
            throw new IllegalArgumentException(String.format("[ERROR] %s은 등교일이 아닙니다.",localDate.format(f1)));
        }
        
        if (dayOfWeek==DayOfWeek.MONDAY){
            return SchoolTime.open(dayOfWeek,
                    LocalTime.of(13,0),LocalTime.of(18,0));
        }

        return SchoolTime.open(dayOfWeek,
                LocalTime.of(10,0),LocalTime.of(18,0));

    }

}
