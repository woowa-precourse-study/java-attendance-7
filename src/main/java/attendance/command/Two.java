package attendance.command;

import attendance.controller.InputView;
import attendance.controller.OutputView;
import attendance.domain.Crew;
import attendance.domain.CrewGroup;
import attendance.service.ModifyDto;
import attendance.service.Service;

import java.time.LocalDate;
import java.time.LocalTime;

public class Two implements Command {
    private final InputView inputView;
    private final Service service;
    private final CrewGroup crewGroup;

    public Two(Service service, CrewGroup crewGroup) {
        this.inputView=new InputView();
        this.service=service;
        this.crewGroup=crewGroup;
    }

    @Override
    public void execute() {
        modifyAttendance();
    }

    public void modifyAttendance() {
        Crew crew = crewGroup.findByName(inputView.readModifyAttendanceNickname());
        LocalDate date = LocalDate.of(2024,12,inputView.readModifyAttendanceDate());
        LocalTime time = inputView.readModifyAttendanceTime();

        ModifyDto.Before before = crewGroup.getDateAndStatus(crew,date);
        ModifyDto.After after = crewGroup.modifyHistory(crew,date,time);

        OutputView.printModifyResult(before,after);

    }
}

