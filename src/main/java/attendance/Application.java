package attendance;

import attendance.controller.AttendanceController;
import attendance.service.AttendanceService;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Application {

    public static void main(String[] args) {

        AttendanceService service = new AttendanceService();
        AttendanceController controller = new AttendanceController(service);
        controller.run();
//        DateTimeFormatter DATE_KOREAN = DateTimeFormatter.ofPattern("HH:mm");
//        parseTime("08:00",DATE_KOREAN);
//        System.out.println(parseTime("08:00",DATE_KOREAN));


    }

//    public static LocalTime parseTime(String input, DateTimeFormatter formatter) {
//        try {
//            return LocalTime.parse(input, formatter);
//        } catch (DateTimeParseException e) {
//            throw new IllegalArgumentException("시간 형식이 올바르지 않습니다.");
//        }
//    }
}

