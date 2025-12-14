package attendance.domain.vo;

import attendance.exception.ErrorMessage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public record Attendance(Date wholeDate,String date, String day, String time) {

    public static Attendance of(Date dateTime) {
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat f2 = new SimpleDateFormat("E요일", Locale.KOREAN);
        SimpleDateFormat f3 = new SimpleDateFormat("HH:mm");
        String date = f1.format(dateTime);
        String day = f2.format(dateTime);
        String time = f3.format(dateTime);
        return new Attendance(dateTime,date, day, time);
    }

    public String returnAttendanceResult() throws ParseException {
        if (date.equals("25") || day.equals("토요일") || day.equals("금요일")){
            throw new IllegalArgumentException(ErrorMessage.INVALID_AT_HOLIDAY.getMessage());
        }
        SimpleDateFormat f3 = new SimpleDateFormat("HH:mm");
        Date todayTime=f3.parse(time);
        if (todayTime.before(f3.parse("08:00")) || todayTime.after(f3.parse("23:00")) ){
            throw new IllegalArgumentException(ErrorMessage.INVALID_TIME.getMessage());
        }
        if (day.equals("월요일")){
            if (todayTime.after(f3.parse("13:05"))){
                return "지각";
            }
            if (todayTime.after(f3.parse("13:30"))){
                return "결석";
            }
        }

        if (!day.equals("월요일") && !day.equals("토요일") || !day.equals("금요일")){
            if (todayTime.after(f3.parse("10:05"))){
                return "지각";
            }
            if (todayTime.after(f3.parse("10:30"))){
                return "결석";
            }
        }
        return "결석";
    }

    public Date getDate(){
        return wholeDate;
    }
}
