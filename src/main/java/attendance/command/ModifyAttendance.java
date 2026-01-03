package attendance.command;

import attendance.controller.InputView;
import attendance.controller.OutputView;
import attendance.domain.SchoolTime;
import attendance.utils.DateUtil;

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
        String name=inputView.readModifyAttendanceName();
        validateName(name);
        String date=inputView.readModifyAttendanceDate();
        String time=inputView.readModifyAttendanceTime();
//        LocalTime localTime= DateUtil.parseTime(time , SchoolTime.DATE_COMPACT);
        List<String> dateTimes=new ArrayList<>();
        for (String nickname:attendances.keySet()){
            if (nickname.equals(name)){
//                dateTimes=attendances.get(name);

            }
        }

        String modifyDate="";
        String targetDate="";
        for (String dateTime:dateTimes){
//            if (DateUtil.getDayofMonth(DateUtil.parseDateTime(dateTime,DateUtil.DATE_TIME)).equals(date)){
//                targetDate=dateTime;
//                dateTimes.remove(dateTime);
//
//
//                LocalDateTime localDateTime= DateUtil.parseDateTime(dateTime,DateUtil.DATE_TIME);
//                String formattedDate=DateUtil.format(localDateTime,DateUtil.DATE);
//                modifyDate=formattedDate+" "+time;
//                dateTimes.add(modifyDate);
//            }
        }
        OutputView.printModifyResult(targetDate,modifyDate);



    }

    private void validateName(String name) {
        if (!attendances.containsKey(name)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
    }
}
