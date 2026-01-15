package attendance.command;

import attendance.controller.InputView;
import attendance.controller.OutputView;
import attendance.domain.*;
import attendance.service.GetDto;
import attendance.service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConfirmAttendance implements Command {
    private final InputView inputView;
    private final Service service;
    private final CrewGroup crewGroup;

    public ConfirmAttendance(Service service, CrewGroup crewGroup) {
        this.inputView = new InputView();
        this.service = service;
        this.crewGroup = crewGroup;
    }

    @Override
    public void execute() {
        confirmAttendance();
    }

    public void confirmAttendance() {
        Crew crew = crewGroup.findByName(inputView.readNickname());
        List<History> histories = crew.getHistories();
        Map<String, Integer> allStatus = crew.getAllStatus();
//        List<History> histories = crewGroup.getHistories(crew);
//        Map<String, Integer> allStatus = crewGroup.getAllStatus(crew);

        crew.updateHistory();
        AttendanceSummary summary = crew.getSummary();
        GetDto.Status status = new GetDto.Status(summary.getAttend(),
                summary.getLate(), summary.getAbsent());

//        GetDto.Status status = new GetDto.Status(allStatus.getOrDefault("출석",0),
//                allStatus.getOrDefault("지각",0),
//                allStatus.getOrDefault("결석",0));

        List<GetDto.Attendance> attendances = new ArrayList<>();
        for (History history : histories) {
            attendances.add(new GetDto.Attendance(history.getDate(), history.getTime(), history.getStatus()));
        }

        String warning = Warning.of(status.getLate(), status.getAbsent()).getName();
        OutputView.printConfirmResult(crew.getName(), new GetDto(attendances, status, warning));

    }
}

