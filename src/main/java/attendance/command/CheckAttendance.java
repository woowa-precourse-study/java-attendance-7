package attendance.command;

import attendance.controller.InputView;


public class CheckAttendance implements Command {
    private final InputView inputView;

    public CheckAttendance(InputView inputView) {
        this.inputView=inputView;
    }

    @Override
    public void execute() {
        checkAttendance();
    }

    public void checkAttendance() {
        // TODO: 작동할거 작성
    }
}

