package attendance.command;

import attendance.controller.InputView;
import attendance.controller.OutputView;
import attendance.domain.SchoolTime;
import attendance.exception.Validator;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;


public class CheckAttendance implements Command {
    private final InputView inputView;
    private Map<String, List<LocalDateTime>> attendances;

    public CheckAttendance(InputView inputView,Map<String,List<LocalDateTime>> attendances) {
        this.inputView=inputView;
        this.attendances=attendances;
    }

    @Override
    public void execute() {
        checkAttendance();
    }

    public void checkAttendance() {
        LocalDateTime today = DateTimes.now();
        SchoolTime schoolTime = SchoolTime.from(today);
        schoolTime.validateWeekDay(today);

        String name = inputView.readNickname();
        validateName(name);

        String time = inputView.readSchoolTime();
        LocalTime localTime=Validator.validateTime(time);
        schoolTime.validateSchoolTime(localTime);



        OutputView.printTodayAttendanceCheck(today,localTime,
                schoolTime.calculateStatus(today.toLocalDate().atTime(localTime)));

    }

    private void validateName(String name) {
        if (!attendances.containsKey(name)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
    }
}

