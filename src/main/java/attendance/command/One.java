package attendance.command;

import attendance.controller.InputView;
import attendance.controller.OutputView;
import attendance.domain.Crew;
import attendance.domain.CrewGroup;
import attendance.domain.SchoolTime;
import attendance.service.Service;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDateTime;
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
        LocalDateTime today= DateTimes.now();
        SchoolTime schoolTime = SchoolTime.of(today.toLocalDate());

        Crew crew = crewGroup.findByName(inputView.readNickname());
        LocalTime time = inputView.readAttendanceTime();
        String status = schoolTime.calculateStatus(time);

        OutputView.printResult(LocalDateTime.of(today.toLocalDate(),time), status);

    }
}

