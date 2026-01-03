package attendance.command;

import attendance.controller.InputView;
import attendance.controller.OutputView;
import attendance.domain.SchoolTime;
import attendance.service.AttendanceDto;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ConfirmAttendance implements Command {
    private final InputView inputView;
    private Map<String, List<LocalDateTime>> attendances;

    public ConfirmAttendance(InputView inputView, Map<String, List<LocalDateTime>> attendances) {
        this.inputView=inputView;
        this.attendances=attendances;
    }

    @Override
    public void execute() {
        confirmAttendance();
    }

    public void confirmAttendance() {

        String name = inputView.readConfirmAttendanceName();
        validateName(name);

        List<LocalDateTime> times=attendances.get(name);
        Collections.sort(times);
        List<AttendanceDto> attendanceDtos=new ArrayList<>();

        for (LocalDateTime localDateTime:times){
            String status = SchoolTime.from(localDateTime).calculateStatus(localDateTime);
            attendanceDtos.add(new AttendanceDto(localDateTime,status));
        }

        OutputView.printModifyResult(name,attendanceDtos);


    }

    private void validateName(String name) {
        if (!attendances.containsKey(name)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
    }
}


