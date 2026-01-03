package attendance.command;

import attendance.controller.InputView;
import attendance.controller.OutputView;
import attendance.domain.SchoolTime;
import attendance.service.AttendanceDto;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDateTime;
import java.util.*;

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
        Map<String,Integer> result = new LinkedHashMap<>();
        result.put("출석",0);
        result.put("지각",0);
        result.put("결석",0);

        for (LocalDateTime localDateTime:times){
            String status = SchoolTime.from(localDateTime).calculateStatus(localDateTime);
            result.put(status,result.getOrDefault(status,0)+1);
            attendanceDtos.add(new AttendanceDto(localDateTime,status));
        }

        OutputView.printModifyResult(name,attendanceDtos);
        OutputView.printModifyWarningResult(result);

        String warning="";
        if (result.get("지각")/3+result.get("결석")>5){
            warning="제적";
        }
        if (result.get("지각")/3+result.get("결석")>=3){
            warning="면담";
        }
        if (result.get("지각")/3+result.get("결석")>=2){
            warning="경고";
        }

        if (!warning.isBlank()){
            OutputView.confirmWarning(warning);
        }
    }

    private void validateName(String name) {
        if (!attendances.containsKey(name)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
    }
}


