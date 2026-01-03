package attendance.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OutputView {

    public static void printTodayAttendanceCheck(LocalDateTime today, LocalTime time, String status){
        DateTimeFormatter f1=DateTimeFormatter.ofPattern("M월 d일 E요일 HH:mm", Locale.KOREAN);
        System.out.println(String.format("%s (%s)", today.toLocalDate().atTime(time).format(f1), status));
    }

    public static void printModifyResult(LocalDateTime before, String beforeStatus, LocalDateTime after, String afterStatus){
        DateTimeFormatter f1=DateTimeFormatter.ofPattern("MM월 dd일 E요일 HH:mm", Locale.KOREAN);
        DateTimeFormatter f2=DateTimeFormatter.ofPattern("HH:mm", Locale.KOREAN);

        System.out.println(String.format("%s (%s) -> %s (%s) 수정 완료!",
                before.format(f1),beforeStatus,after.format(f2),afterStatus));
    }
}

