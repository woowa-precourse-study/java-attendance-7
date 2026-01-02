package attendance.command;

import attendance.controller.InputView;
import attendance.controller.OutputView;
import attendance.domain.SchoolTime;
import attendance.utils.DateUtil;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


public class CheckAttendance implements Command {
    private final InputView inputView;

    public CheckAttendance(InputView inputView) {
        this.inputView=inputView;
    }

    @Override
    public void execute() {
        checkAttendance();
    }

    public void checkAttendance() {
        LocalDateTime today = DateTimes.now();
//        LocalDateTime today = LocalDate.of(2026, 1, 3).atStartOfDay();
        SchoolTime schoolTime = SchoolTime.fromDayofWeek(DateUtil.getDayOfWeek(today));
        schoolTime.validateSchoolTime(today);
        inputView.readNickname();
        LocalDateTime localDateTime=DateUtil.parseTime(inputView.readSchoolTime(),SchoolTime.DATE_COMPACT);
        schoolTime.validateSchoolTime(localDateTime);
        OutputView.printTodayAttendanceCheck(today,schoolTime.calculateStatus(localDateTime));

    }
}

