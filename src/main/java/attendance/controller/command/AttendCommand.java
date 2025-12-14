package attendance.controller.command;

import static attendance.util.Validator.validateNumber;
import static attendance.view.PrintMessage.INPUT_NICKNAME;
import static attendance.view.PrintMessage.INPUT_TIME;

import attendance.controller.Command;
import attendance.domain.Attendance;
import attendance.domain.AttendanceSheet;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class AttendCommand implements Command {
    private static final String TIME_DELIMITER = ":";

    @Override
    public void execute(AttendanceSheet sheet) {
        OutputView.printPrompt(INPUT_NICKNAME);
        String name = InputView.readNickName();

        OutputView.printPrompt(INPUT_TIME);
        String time = InputView.readTime();

        List<String> hourAndMinute = List.of(time.split(TIME_DELIMITER));
        validateTime(hourAndMinute);

        String hour = hourAndMinute.getFirst();
        String minutes = hourAndMinute.getLast();

        LocalTime localTime = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minutes));
        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now(), localTime);

        Attendance attendance = sheet.attendBy(name, localDateTime);
        OutputView.printAttendance(attendance);
    }

    private void validateTime(List<String> hourAndMinute) {
        if (hourAndMinute.size() != 2) {
            throw new IllegalStateException("잘못된 형식을 입력하셨습니다.");
        }

        validateNumber(hourAndMinute.getFirst());
        validateNumber(hourAndMinute.getLast());
    }
}
