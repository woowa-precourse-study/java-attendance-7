package attendance.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OutputView {
    public static void printResult(LocalDateTime dateTime, String status){
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MM월 dd일 E요일 HH:mm", Locale.KOREAN);
        System.out.printf("%s (%s)\n",dateTime.format(f1),status);
    }
}
