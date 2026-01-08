package attendance.command;

import attendance.controller.InputView;
import attendance.controller.OutputView;
import attendance.domain.*;
import attendance.service.GetDto;
import attendance.service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        confirmAttendance();
    }

    public void confirmAttendance() {
        Crew crew = crewGroup.findByName(inputView.readNickname());
        List<History> histories = crewGroup.getHistories(crew);
        Map<String, Long> allStatus = crewGroup.getAllStatus(crew);

        GetDto.Status status = new GetDto.Status(Math.toIntExact(allStatus.getOrDefault("출석",0L)),
                Math.toIntExact(allStatus.getOrDefault("지각",0L)),
                Math.toIntExact(allStatus.getOrDefault("결석",0L)));

        List<GetDto.Attendance> attendances = new ArrayList<>();
        for (History history : histories){
            attendances.add(new GetDto.Attendance(history.getDate(),history.getTime(),history.getStatus()));
        }

        String warning = Warning.of(status.getLate(),status.getAbsent()).getName();

        OutputView.printConfirmResult(crew.getName(),new GetDto(attendances,status,warning));

    }
}

