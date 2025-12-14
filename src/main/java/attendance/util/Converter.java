package attendance.util;

import java.time.DayOfWeek;

public final class Converter {

    public static String convertDayOfWeekToString(DayOfWeek dayOfWeek) {
        if (dayOfWeek.equals(DayOfWeek.MONDAY)) {
            return "월요일";
        }

        if (dayOfWeek.equals(DayOfWeek.TUESDAY)) {
            return "화요일";
        }

        if (dayOfWeek.equals(DayOfWeek.WEDNESDAY)) {
            return "수요일";
        }

        if (dayOfWeek.equals(DayOfWeek.THURSDAY)) {
            return "목요일";
        }

        if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
            return "금요일";
        }

        if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
            return "토요일";
        }

        return "일요일";
    }
}
