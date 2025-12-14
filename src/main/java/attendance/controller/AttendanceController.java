package attendance.controller;

import attendance.domain.AttendanceSheet;
import attendance.view.InputView;
import attendance.view.OutputView;
import attendance.view.PrintMessage;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.util.Map;

public class AttendanceController {
    private final AttendanceSheet sheet;
    private final Map<String, Command> commands;

    public AttendanceController(AttendanceSheet sheet, Map<String, Command> commands) {
        this.sheet = sheet;
        this.commands = commands;
    }

    public void run() {
        try {
            while (true) {
                LocalDateTime now = DateTimes.now();
                OutputView.printTodayInfo(now);

                OutputView.printPrompt(PrintMessage.COMMAND_INFO);
                String inputCommand = InputView.readCommand();
                Command command = commands.get(inputCommand);
                validateCommand(command);

                command.execute(sheet);
            }
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        } catch (IllegalStateException e) {
            OutputView.printPrompt(e.getMessage());
        }
    }

    private void validateCommand(Command command) {
        if (command == null) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }
    }
}
