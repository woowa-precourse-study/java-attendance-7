package attendance.controller;


import attendance.service.ModifyDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OutputView {
    public static void printResult(LocalDateTime dateTime, String status){
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MM월 dd일 E요일 HH:mm", Locale.KOREAN);
        System.out.printf("%s (%s)\n",dateTime.format(f1),status);
    }

    public static void printModifyResult(ModifyDto.Before before, ModifyDto.After after){
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MM월 dd일 E요일", Locale.KOREAN);
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("HH:mm", Locale.KOREAN);

        System.out.printf("%s %s (%s) -> %s (%s) 수정 완료!\n",
                before.getDate().format(f1),before.getTime().format(f2),before.getStatus(),
                after.getTime().format(f2),after.getStatus());
    }
}
