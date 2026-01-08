package attendance.command;

import attendance.controller.InputView;
import attendance.domain.Crew;
import attendance.domain.CrewGroup;
import attendance.service.Service;

import java.time.LocalTime;

public class One implements Command {
    private final InputView inputView;
    private final Service service;
    private final CrewGroup crewGroup;

    public One(Service service, CrewGroup crewGroup) {
        this.inputView = new InputView();
        this.service = service;
        this.crewGroup = crewGroup;
    }

    @Override
    public void execute() {
        checkAttendance();
    }

    public void checkAttendance() {
        inputView.readNickname();
        LocalTime time = inputView.readAttendanceTime();

    }
}

