package attendance.command;

import attendance.controller.InputView;
import attendance.domain.*;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ConfirmAttendance implements Command {
    private final InputView inputView;
    private final CrewGroup crewGroup;

    public ConfirmAttendance(InputView inputView, CrewGroup crewGroup) {
        this.inputView = inputView;
        this.crewGroup = crewGroup;
    }

    @Override
    public void execute() {
        confirmAttendance();
    }

    public void confirmAttendance() {
        String name = Console.readLine();
        if (!crewGroup.containCrew(name)){
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }

        Crew crew = crewGroup.findByName(name);

        LocalDate start = LocalDate.of(2024,12, 1);
        LocalDate end = DateTimes.now().toLocalDate();

        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MM월 dd일 E요일 HH:mm", Locale.KOREAN);
        DateTimeFormatter f2 = DateTimeFormatter.ofPattern("MM월 dd일 E요일", Locale.KOREAN);
        for (LocalDate date=start; date.isBefore(end);date=date.plusDays(1)){
            try{
                Attendance attendance = crew.findAttendance(date);
                System.out.printf("%s (%s)\n",attendance.getLocalDateTime().format(f1), attendance.getStatus().getName());
            } catch(IllegalArgumentException e){
                if (date.getDayOfWeek()!= DayOfWeek.SATURDAY && date.getDayOfWeek()!= DayOfWeek.SUNDAY){
                    System.out.printf("%s --:-- (%s)\n",date.format(f2),Attendance.Status.ABSENT.getName());
                }

            }


        }

    }

}

