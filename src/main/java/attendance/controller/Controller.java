package attendance.controller;

import attendance.command.*;
import attendance.service.Service;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private Map<String, Command> commands = new HashMap<>();
    private final InputView inputView;
    private final Service service;

    public Controller(Service service) {
        this.inputView = new InputView();
        this.service = service;
    }

    public void run() {
        LocalDateTime today=DateTimes.now();
        while (true){
            String function =inputView.readFunction(today.toLocalDate());
            if (function.equals("Q")){
                break;
            }
            Command command = commands.get(function);
            command.execute();
        }

    }

    private void initCommands() {
        commands.put("1", new One(service));
        commands.put("2", new Two(service));
        commands.put("3", new Three(service));
        commands.put("4", new Four(service));
        commands.put("Q", new Quit());
    }


}

