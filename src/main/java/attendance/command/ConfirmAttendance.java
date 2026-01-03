package attendance.command;

import attendance.controller.InputView;

public class ConfirmAttendance implements Command {
    private final InputView inputView;

    public ConfirmAttendance(InputView inputView) {
        this.inputView=inputView;
    }

    @Override
    public void execute() {
        confirmAttendance();
    }

    public void confirmAttendance() {

    }
}


