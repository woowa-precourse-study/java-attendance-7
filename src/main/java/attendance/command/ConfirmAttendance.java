package attendance.command;

import attendance.controller.InputView;
import attendance.domain.CrewGroup;
import camp.nextstep.edu.missionutils.Console;

public class ConfirmAttendance implements Command {
    private final InputView inputView;
    private final CrewGroup crewGroup;

    public ConfirmAttendance(InputView inputView, CrewGroup crewGroup) {
        this.inputView = inputView;
        this.crewGroup = crewGroup;
    }

    @Override
    public void execute() {
        confirmAttendance();
    }

    public void confirmAttendance() {
        String name = Console.readLine();
        String time = Console.readLine();
        crewGroup.findByName(name);
    }

}

