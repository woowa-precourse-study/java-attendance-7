package attendance.controller;

import attendance.exception.Validator;
import camp.nextstep.edu.missionutils.Console;

import java.util.List;
import java.util.NoSuchElementException;

public class InputView {

    public String readMessage() {
        System.out.println("");
        String input = readInput(List.of(
                Validator::validateNotBlank
        ));
        return input;
    }

    private String readInput(List<Validator> validators) {
        String input = Console.readLine().trim();
        for (Validator v : validators) {
            v.validate(input);
        }
        return input;
    }
}