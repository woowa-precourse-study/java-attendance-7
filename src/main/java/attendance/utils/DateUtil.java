package attendance.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

public final class DateUtil {
    public static final DateTimeFormatter DATE_TIME =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATE =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter TIME =
            DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter DATE_DAY_OF_WEEK =
            DateTimeFormatter.ofPattern("MM월 dd일 E요일 HH:mm", Locale.KOREAN);

    private DateUtil() {
    }

    // 요일 구하기
    public static String getDayOfWeek(LocalDateTime date) {
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.KOREAN);
    }

    public static String getMonth(LocalDateTime date) {
        return date.getMonth().getDisplayName(TextStyle.FULL, Locale.KOREAN);
    }

    public static String getDayofMonth(LocalDateTime date) {
        return date.getDayOfMonth() + "일";
    }

    public static int getHour(LocalDateTime dateTime) {
        return dateTime.getHour(); // 0 ~ 23
    }

    public static int getMinute(LocalDateTime dateTime) {
        return dateTime.getMinute(); // 0 ~ 59
    }

    public static String formatTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }


    public static String format(LocalDateTime dateTime, DateTimeFormatter formatter) {
        return dateTime.format(formatter);
    }

    public static LocalDateTime parseDateTime(String input, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("날짜/시간 형식이 올바르지 않습니다.");
        }
    }

    public static LocalTime parseTime(String input, DateTimeFormatter formatter) {
        try {
            return LocalTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    public static LocalTime addMinutes(LocalTime time, int minutes) {
        return time.plusMinutes(minutes);
    }

    public static String getFulldate(LocalDateTime date) {
        return String.format("%s %s %s", getMonth(date), getDayofMonth(date), getDayOfWeek(date));
    }

}

