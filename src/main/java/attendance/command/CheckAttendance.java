package attendance.command;

import attendance.controller.InputView;
import attendance.controller.OutputView;
import attendance.domain.SchoolTime;
import attendance.utils.DateUtil;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;


public class CheckAttendance implements Command {
    private final InputView inputView;
    private Map<String, List<String>> attendances;

    public CheckAttendance(InputView inputView,Map<String,List<String>> attendances) {
        this.inputView=inputView;
        this.attendances=attendances;
    }

    @Override
    public void execute() {
        checkAttendance();
    }

    public void checkAttendance() {
        LocalDateTime today = DateTimes.now();
//        LocalDateTime today = LocalDate.of(2026, 1, 3).atStartOfDay();
        SchoolTime schoolTime = SchoolTime.fromDayofWeek(DateUtil.getDayOfWeek(today));
        schoolTime.validateWeekDay(schoolTime,today);

        String name = inputView.readNickname();
        if (!attendances.containsKey(name)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
        String time = inputView.readSchoolTime();
        LocalTime localTime=DateUtil.parseTime(time ,SchoolTime.DATE_COMPACT);
        schoolTime.validateSchoolTime(schoolTime,localTime);
        OutputView.printTodayAttendanceCheck(today,time ,schoolTime.calculateStatus(localTime));

    }
}

