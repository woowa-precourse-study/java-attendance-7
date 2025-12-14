package attendance.domain.vo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public record Attendance(String name, String date, String day, String time) {

    public static Attendance from(String name, Date dateTime) {
        SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat f2 = new SimpleDateFormat("E요일", Locale.KOREAN);
        SimpleDateFormat f3 = new SimpleDateFormat("HH:mm");
        String date = f1.format(dateTime);
        String day =f2.format(dateTime);
        String time =f3.format(dateTime);
        return new Attendance(name,date,day,time);
    }


}
