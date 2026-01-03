package attendance.service;

import java.time.LocalDateTime;

public record AttendanceDto(
        LocalDateTime localDateTime,
        String status
) {
}
