package attendance.command;

import attendance.controller.InputView;
import attendance.domain.*;
import attendance.exception.Validator;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CheckAttendance implements Command {
    private final InputView inputView;
    private final CrewGroup crewGroup;

    public CheckAttendance(InputView inputView, CrewGroup crewGroup) {
        this.inputView=inputView;
        this.crewGroup=crewGroup;
    }

    @Override
    public void execute() {
        checkAttendance();
    }

    public void checkAttendance() {
        LocalDateTime localDateTime = DateTimes.now();
        SchoolTime schoolTime = new SchoolCalendar().schoolTimeOf(localDateTime.toLocalDate());
        String name = Console.readLine();
        if (!crewGroup.containCrew(name)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
        String time = Console.readLine();
        LocalTime localTime=Validator.validateTime(time);
        Crew crew = crewGroup.findByName(name);
        LocalDateTime arrivedTime= LocalDateTime.of(localDateTime.toLocalDate(),localTime);
        crew.addAttendance(arrivedTime);
        Attendance attendance = crew.findAttendance(arrivedTime.toLocalDate());
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("M월 d일 E요일 HH:mm", Locale.KOREAN);

        System.out.println(String.format("%s (%s)",arrivedTime.format(f1),attendance.getStatus().getName()));

    }
}

