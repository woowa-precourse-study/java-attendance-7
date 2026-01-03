package attendance.controller;

import attendance.utils.DateUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OutputView {

    public static void printTodayAttendanceCheck(LocalDateTime today, LocalTime time, String status){
        DateTimeFormatter f1=DateTimeFormatter.ofPattern("M월 d일 E요일 HH:mm", Locale.KOREAN);
        System.out.println(String.format("%s (%s)", today.toLocalDate().atTime(time).format(f1), status));
    }

    public static void printModifyResult(String targetDate,String modifyDate){
        LocalDateTime before=DateUtil.parseDateTime(targetDate,DateUtil.DATE_TIME);
        LocalDateTime after=DateUtil.parseDateTime(modifyDate,DateUtil.DATE_TIME);

        System.out.println(String.format("%s %s",
                DateUtil.format(before,DateUtil.DATE_DAY_OF_WEEK), DateUtil.format(after,DateUtil.DATE_DAY_OF_WEEK)));
    }
}

