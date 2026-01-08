package attendance.command;

import attendance.controller.InputView;
import attendance.controller.OutputView;
import attendance.domain.CrewGroup;
import attendance.service.Service;
import attendance.service.WarningDto;

public class Four implements Command {
    private final InputView inputView;
    private final Service service;
    private final CrewGroup crewGroup;


    public Four(Service service, CrewGroup crewGroup) {
        this.inputView = new InputView();
        this.service = service;
        this.crewGroup = crewGroup;
    }

    @Override
    public void execute() {
        checkAttendance();
    }

    public void checkAttendance() {
        WarningDto dto = crewGroup.getWarning();
        OutputView.printWarnResult(dto);
    }
}

