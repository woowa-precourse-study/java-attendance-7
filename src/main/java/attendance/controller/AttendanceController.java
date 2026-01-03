package attendance.controller;

import attendance.command.Command;
import attendance.command.CheckAttendance;
import attendance.command.Quit;
import attendance.command.ModifyAttendance;
import attendance.service.AttendanceService;
import camp.nextstep.edu.missionutils.DateTimes;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;


public class AttendanceController {
    private Map<String, Command> commands = new HashMap<>();
    private Map<String,List<String>> attendances = new HashMap<>();
    private final InputView inputView;
    private final AttendanceService service;
    static final int MAX_RETRY = 10;

    public AttendanceController(AttendanceService service) {
        this.inputView = new InputView();
        this.service = service;
        initSettings();
        initCommands();

    }

    public void run() {
        while (true) {
            LocalDateTime today = DateTimes.now();
            String function = inputView.readMessage(today);
            if (function.equals("Q")) {
                break;
            }
            Command command = commands.get(function);
            command.execute();
        }
    }

    private void initCommands() {
        commands.put("1", new CheckAttendance(inputView,attendances));
        commands.put("2", new ModifyAttendance(inputView,attendances));
//        commands.put("3", new Three(inputView));
//        commands.put("4", new Four(inputView));
        commands.put("Q", new Quit());
    }

    private void initSettings() {
        attendances = readFile();

    }
    private Map<String,List<String>> readFile() {
        Map<String,List<String>> attendances=new HashMap<>();

        try{
            BufferedReader br = Files.newBufferedReader(Path.of("src/main/resources/attendances.csv"));
            br.readLine(); // header skip

            String line;
            while((line=br.readLine())!=null){

                String[] cols = line.split(",");

                List<String> times=attendances.getOrDefault(cols[0],new ArrayList<>());
                times.add(cols[1]);
                attendances.put(cols[0],times);
            }
        } catch (IOException e) {
            throw new IllegalStateException("파일을 읽는데 오류가 발생했습니다.");
        }
        return attendances;
    }

    public static List<String> splitBy(String input, String symbols) {
        List<String> result = Arrays.stream(input.split(symbols, -1))
                .map(String::trim)
                .toList();

        if (result.stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException("[ERROR] 빈 값이 포함되어 있습니다.");
        }

        return result;
    }




}

