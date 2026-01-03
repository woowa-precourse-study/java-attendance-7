package attendance.command;

import attendance.controller.InputView;
import attendance.controller.OutputView;
import attendance.domain.SchoolTime;
import attendance.exception.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModifyAttendance implements Command{
    private final InputView inputView;
    private Map<String, List<LocalDateTime>> attendances;

    public ModifyAttendance(InputView inputView,Map<String,List<LocalDateTime>> attendances) {
        this.inputView=inputView;
        this.attendances=attendances;
    }

    @Override
    public void execute() {
        modifyAttendance();
    }

    public void modifyAttendance() {
        //        LocalDateTime today = DateTimes.now();


        String name=inputView.readModifyAttendanceName();
        validateName(name);

        int date=inputView.readModifyAttendanceDate();
        LocalDateTime modifyDate = LocalDate.of(2024, 12, date).atStartOfDay();
        SchoolTime schoolTime = SchoolTime.from(modifyDate);
        schoolTime.validateWeekDay(modifyDate);

        String time=inputView.readModifyAttendanceTime();
        LocalTime localTime= Validator.validateTime(time);
        List<LocalDateTime> times=attendances.get(name);

        LocalDateTime before=null;
        for (LocalDateTime dateTime:times){
            if (dateTime.getDayOfMonth()==date){
                before=dateTime;
                break;
            }
        }
        if (before==null){
            throw new IllegalArgumentException("[ERROR] 해당 날짜 출석 기록이 없습니다.");
        }

        times.remove(before);
        LocalDateTime after=LocalDateTime.of(before.toLocalDate(),localTime);
        times.add(after);
        OutputView.printModifyResult(before,schoolTime.calculateStatus(before),
                after,schoolTime.calculateStatus(after));

    }

    private void validateName(String name) {
        if (!attendances.containsKey(name)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
    }
}
