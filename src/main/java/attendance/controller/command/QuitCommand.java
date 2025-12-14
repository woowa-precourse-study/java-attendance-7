package attendance.controller.command;

import attendance.controller.Command;
import attendance.domain.AttendanceSheet;

public class QuitCommand implements Command {
    @Override
    public void execute(AttendanceSheet sheet) {
        throw new IllegalStateException("");
    }
}
