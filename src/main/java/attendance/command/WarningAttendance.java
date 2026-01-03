package attendance.command;

import attendance.controller.InputView;
import attendance.domain.CrewGroup;

public class WarningAttendance implements Command {
    private final InputView inputView;
    private final CrewGroup crewGroup;


    public WarningAttendance(InputView inputView, CrewGroup crewGroup) {
        this.inputView=inputView;
        this.crewGroup=crewGroup;
    }

    @Override
    public void execute() {
        warningAttendance();
    }

    public void warningAttendance() {

    }
}

