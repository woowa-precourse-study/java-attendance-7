package attendance.controller;

import attendance.command.*;
import attendance.domain.Crew;
import attendance.domain.CrewGroup;
import attendance.domain.History;
import attendance.domain.SchoolTime;
import attendance.service.Service;
import camp.nextstep.edu.missionutils.DateTimes;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private Map<String, Command> commands = new HashMap<>();
    private final InputView inputView;
    private final Service service;
    private final CrewGroup crewGroup;

    public Controller(Service service) {
        this.inputView = new InputView();
        this.service = service;
        this.crewGroup = new CrewGroup();
        initCommands();
    }

    public void run() {
        initSetting();
        while (true){
            LocalDateTime today=DateTimes.now();
            String function =inputView.readFunction(today.toLocalDate());
            if (function.equals("Q")){
                break;
            }
            Command command = commands.get(function);
            command.execute();
        }

    }

    private void initCommands() {
        commands.put("1", new CheckAttendance(service,crewGroup));
        commands.put("2", new ModifyAttendance(service,crewGroup));
        commands.put("3", new ConfirmAttendance(service,crewGroup));
        commands.put("4", new Four(service,crewGroup));
        commands.put("Q", new Quit());
    }

    public void initSetting(){
        readFile();
        LocalDateTime today=DateTimes.now();
        LocalDate start = LocalDate.of(2024,12,1);
        LocalDate end = today.toLocalDate();
        for (LocalDate date = start;date.isBefore(end);date=date.plusDays(1)){
            crewGroup.addOmittedHistory(date);
        }
    }

    public void readFile() {
        try{
            BufferedReader br = Files.newBufferedReader(Path.of("src/main/resources/attendances.csv"));
            br.readLine(); // header skip

            String line;
            while((line=br.readLine())!=null){

                String[] cols = line.split(",");
                try {
                    Crew crew = crewGroup.findByName(cols[0]);
                } catch (IllegalArgumentException ignored){
                    crewGroup.add(new Crew(cols[0]));
                }

                Crew crew = crewGroup.findByName(cols[0]);

                DateTimeFormatter p1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime localDateTime = LocalDateTime.parse(cols[1],p1);
                SchoolTime schoolTime = SchoolTime.of(localDateTime.toLocalDate());
                String status = schoolTime.calculateStatus(localDateTime.toLocalTime());

                crew.addHistory(new History(localDateTime.toLocalDate(),
                        localDateTime.toLocalTime(),schoolTime,status));
            }
        } catch (IOException e) {
            throw new IllegalStateException("파일을 읽는데 오류가 발생했습니다.");
        }
    }


}

