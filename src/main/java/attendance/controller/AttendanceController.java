package attendance.controller;

import attendance.command.*;
import attendance.domain.CrewGroup;
import attendance.service.AttendanceService;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class AttendanceController {
    private final Map<String, Command> commands = new HashMap<>();
    private final CrewGroup crewGroup=new CrewGroup();
    private final InputView inputView;
    private final AttendanceService service;

    public AttendanceController(AttendanceService service) {
        this.inputView = new InputView();
        this.service = service;
        initCommands();
        readFile();
    }

    public void run() {
        while (true) {
            String function = inputView.readMessage();
            if (function.equals("Q")) {
                break;
            }
            Command command = commands.get(function);
            command.execute();
        }
    }

    private void initCommands() {
        commands.put("1", new CheckAttendance(inputView,crewGroup));
        commands.put("2", new ModifyAttendance(inputView,crewGroup));
        commands.put("3", new ConfirmAttendance(inputView,crewGroup));
        commands.put("4", new WarningAttendance(inputView,crewGroup));
        commands.put("Q", new Quit());
    }

    private void readFile(){
        try{
            BufferedReader br = Files.newBufferedReader(Path.of("src/main/resources/attendances.csv"));
            br.readLine(); // header skip

            String line;
            while((line=br.readLine())!=null){

                String[] cols = line.split(",");
                if (!crewGroup.containCrew(cols[0])){
                    crewGroup.addCrew(cols[0]);
                }

                DateTimeFormatter p1=DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm");
                LocalDateTime localDateTime=LocalDateTime.parse(cols[1],p1);
                crewGroup.findByName(cols[0]).addAttendance(localDateTime);

            }
        } catch (IOException e) {
            throw new IllegalStateException("파일을 읽는데 오류가 발생했습니다.");
        }
    }


}


