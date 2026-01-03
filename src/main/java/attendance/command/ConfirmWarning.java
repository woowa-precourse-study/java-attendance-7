package attendance.command;

import attendance.controller.InputView;

public class ConfirmWarning implements Command {
    private final InputView inputView;

    public ConfirmWarning(InputView inputView) {
        this.inputView=inputView;
    }

    @Override
    public void execute() {
        confirmWarning();
    }

    public void confirmWarning() {

    }
}

