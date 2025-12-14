package attendance.dto;

import java.util.Date;

public class ResponseDto {
    public record todayAttendance(Date today, String result){
    }
}
