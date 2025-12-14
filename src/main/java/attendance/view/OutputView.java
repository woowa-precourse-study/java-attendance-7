package attendance.view;

import attendance.domain.Attendance;
import attendance.domain.AttendanceState;
import attendance.util.Converter;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void printPrompt(String message) {
        System.out.println(message);
    }

    public static void printAttendance(Attendance attendance) {
        String name = attendance.getName();
        LocalDateTime localDateTime = attendance.getTime();
        AttendanceState state = attendance.getState();

        LocalTime time = localDateTime.toLocalTime();
        DayOfWeek day = localDateTime.getDayOfWeek();
        String dayOfWeek = Converter.convertDayOfWeekToString(day);

        System.out.printf("%d월 %d일 %s %d:%d %s%n",
                localDateTime.getMonthValue(), localDateTime.getDayOfMonth(), dayOfWeek, time.getHour(),
                time.getMinute(), state.getName()
        );
    }

    public static void printTodayInfo(LocalDateTime now) {
        String dayOfWeek = Converter.convertDayOfWeekToString(now.getDayOfWeek());
        System.out.printf(PrintMessage.TODAY_INFO, now.getMonthValue(), now.getDayOfMonth(), dayOfWeek);
    }

    public static void printError(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
