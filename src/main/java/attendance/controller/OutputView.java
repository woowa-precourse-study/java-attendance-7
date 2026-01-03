package attendance.controller;

import attendance.utils.DateUtil;

import java.time.LocalDateTime;

public class OutputView {

    public static void printTodayAttendanceCheck(LocalDateTime today,String time,String status){
        System.out.println(String.format("%s %s (%s)", DateUtil.getFulldate(today), time,status));
    }

    public static void printModifyResult(String targetDate,String modifyDate){
        LocalDateTime before=DateUtil.parseDateTime(targetDate,DateUtil.DATE_TIME);
        LocalDateTime after=DateUtil.parseDateTime(modifyDate,DateUtil.DATE_TIME);

        System.out.println(String.format("%s %s",
                DateUtil.format(before,DateUtil.DATE_DAY_OF_WEEK), DateUtil.format(after,DateUtil.DATE_DAY_OF_WEEK)));
    }
}

