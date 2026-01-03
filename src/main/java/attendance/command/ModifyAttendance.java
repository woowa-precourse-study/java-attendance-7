package attendance.command;

import attendance.controller.InputView;
import attendance.domain.Attendance;
import attendance.domain.Crew;
import attendance.domain.CrewGroup;
import attendance.exception.Validator;
import camp.nextstep.edu.missionutils.Console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ModifyAttendance implements Command {
    private final InputView inputView;
    private final CrewGroup crewGroup;

    public ModifyAttendance(InputView inputView, CrewGroup crewGroup) {
        this.inputView=inputView;
        this.crewGroup=crewGroup;
    }

    @Override
    public void execute() {
        modifyAttendance();
    }

    public void modifyAttendance() {
        String name = Console.readLine();
        if (!crewGroup.containCrew(name)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
        int date = Validator.validateIsNumber(Console.readLine());
        String time = Console.readLine();
        LocalTime localTime= Validator.validateTime(time);
        Crew crew = crewGroup.findByName(name);
        Attendance attendance = crew.findAttendance(LocalDate.of(2024,12,date));

        List<String> status = new ArrayList<>();
        LocalDateTime localDateTime = attendance.getLocalDateTime();
        status.add(attendance.getStatus().getName());
        attendance.changeLocalTime(localTime);
        status.add(attendance.getStatus().getName());

        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MM월 dd일 E요일 HH:mm", Locale.KOREAN);
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println(String.format("%s (%s) -> %s (%s) 수정 완료!",
                localDateTime.format(f1),status.get(0),localTime.format(f2),status.get(1)));



    }
}


