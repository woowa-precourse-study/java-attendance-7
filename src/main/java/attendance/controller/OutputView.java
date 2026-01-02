package attendance.controller;

import attendance.utils.DateUtil;

import java.time.LocalDateTime;

public class OutputView {

    public static void printTodayAttendanceCheck(LocalDateTime today,String status){
        System.out.println(String.format("%s %s (%s)", DateUtil.getFulldate(today), DateUtil.formatTime(today),status));
    }
}

