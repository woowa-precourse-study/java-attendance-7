package attendance.command;

import attendance.controller.InputView;
import attendance.service.Service;

public class Two implements Command {
    private final InputView inputView;
    private final Service service;

    public Two(Service service) {
        this.inputView=new InputView();
        this.service=service;
    }

    @Override
    public void execute() {
        checkAttendance();
    }

    public void checkAttendance() {

    }
}

