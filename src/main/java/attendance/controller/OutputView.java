package attendance.controller;

import attendance.dto.ResponseDto;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class OutputView {
    void printTodayAttendance(ResponseDto.todayAttendance response){
        SimpleDateFormat f1 = new SimpleDateFormat("MM월 dd일 E요일 HH:mm", Locale.KOREAN);
        System.out.println(f1.format(response.today())+String.format(" (%s)",response.result()));
    }

    void printErrorMessage(String message){
        System.out.println(message);
    }

}
