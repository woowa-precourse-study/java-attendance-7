package attendance.dto;

import java.time.LocalDateTime;

public class RequestDto {
    public record todayAttendance(String Nickname, LocalDateTime today){
    }
}
