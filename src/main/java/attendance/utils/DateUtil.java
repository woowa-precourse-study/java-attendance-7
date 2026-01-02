package attendance.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public final class DateUtil {

    private DateUtil() {
    }

    // 요일 구하기
    public static String getDayOfWeek(LocalDateTime date) {
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN); //토
    }

    public static String getMonth(LocalDateTime date) {
        return date.getMonth().getDisplayName(TextStyle.FULL, Locale.KOREAN);
    }

    public static String getDayofMonth(LocalDateTime date) {
        return date.getDayOfMonth()+"일";
    }

    public static String getFulldate(LocalDateTime date){
        return String.format("%s %s %s",getMonth(date),getDayofMonth(date),getDayOfWeek(date));
    }




}

