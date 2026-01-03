package attendance.command;

import attendance.controller.InputView;
import attendance.controller.OutputView;
import attendance.domain.SchoolTime;
import attendance.exception.Validator;
import attendance.utils.DateUtil;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;
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
//        LocalDateTime today = DateTimes.now();
        LocalDateTime today = LocalDateTime.of(2026, 1, 3,8,0);
        SchoolTime schoolTime = SchoolTime.from(today);
        schoolTime.validateWeekDay(today);
        schoolTime.validateSchoolTime(today.toLocalTime());

        String name = inputView.readNickname();
        validateName(name);

        String[] time = inputView.readSchoolTime().split(":");
        LocalTime localTime = Validator.validateTime(time[0],time[1]);
        schoolTime.validateSchoolTime(localTime);

        OutputView.printTodayAttendanceCheck(today,localTime,
                schoolTime.calculateStatus(today.toLocalDate().atTime(localTime)));

    }

    private void validateName(String name) {
        if (!attendances.containsKey(name)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
    }

//    private int[] validateTime(String hour,String minute) {
//        int h=Validator.validateIsNumber(hour);
//        int m=Validator.validateIsNumber(minute);
//        int[] arr = {h, m};
//        if (h>24 || h<0){
//            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
//        }
//
//        if (m>60 || m<0){
//            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
//        }
//        return arr;
//    }
}

