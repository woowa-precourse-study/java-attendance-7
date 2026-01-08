package attendance.controller;


import attendance.domain.History;
import attendance.service.GetDto;
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

    public static void printConfirmResult(String name , GetDto getDto){
        System.out.printf("\n이번 달 %s의 출석 기록입니다.\n",name);

        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MM월 dd일 E요일", Locale.KOREAN);
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("HH:mm", Locale.KOREAN);

        for (GetDto.Attendance attendance: getDto.getAttendances()){
            if (attendance.getTime()!=null){
                System.out.printf("%s %s (%s)\n",attendance.getDate().format(f1),attendance.getTime().format(f2),attendance.getStatus());
                continue;
            }
            System.out.printf("%s --:-- (%s)\n",attendance.getDate().format(f1),attendance.getStatus());
        }

        System.out.printf("출석: %s회\n",getDto.getStatus().getAttend());
        System.out.printf("지각: %s회\n",getDto.getStatus().getLate());
        System.out.printf("결석: %s회\n",getDto.getStatus().getAbsent());

        if (!getDto.getWarning().equals("없음")){
            System.out.printf("\n%s 대상자입니다.",getDto.getWarning());
        }
    }

}
