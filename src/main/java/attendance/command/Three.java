package attendance.command;

import attendance.controller.InputView;
import attendance.domain.CrewGroup;
import attendance.service.Service;

public class Three implements Command {
    private final InputView inputView;
    private final Service service;
    private final CrewGroup crewGroup;

    public Three(Service service, CrewGroup crewGroup) {
        this.inputView=new InputView();
        this.service=service;
        this.crewGroup=crewGroup;
    }

    @Override
    public void execute() {
        checkAttendance();
    }

    public void checkAttendance() {

    }
}

