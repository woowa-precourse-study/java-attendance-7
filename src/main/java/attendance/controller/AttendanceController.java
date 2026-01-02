package attendance.controller;

import attendance.command.Command;
import attendance.command.CheckAttendance;
import attendance.command.Quit;
import attendance.service.AttendanceService;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class AttendanceController {
    private Map<String, Command> commands = new HashMap<>();
    private final InputView inputView;
    private final AttendanceService service;
    static final int MAX_RETRY = 10;

    public AttendanceController(AttendanceService service) {
        this.inputView = new InputView();
        this.service = service;
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
        commands.put("1", new CheckAttendance(inputView));
//        commands.put("2", new Two(inputView));
//        commands.put("3", new Three(inputView));
//        commands.put("4", new Four(inputView));
        commands.put("Q", new Quit());
    }

    public void checkAttendance(){

    }







}

