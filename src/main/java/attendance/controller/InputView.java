package attendance.controller;

import attendance.exception.Validator;
import attendance.utils.DateUtil;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

public class InputView {

    public String readMessage(LocalDateTime today) {
        System.out.println("오늘은 "+DateUtil.getFulldate(today)+"입니다. 기능을 선택해 주세요.");
        String input = readInput(List.of(
                Validator::validateNotBlank,
                Validator::validateFunction
        ));
        return input;
    }

    private String readInput(List<Validator> validators) {
        try{
            String input = Console.readLine().trim();
            for (Validator v : validators) {
                v.validate(input);
            }
            return input;
        } catch(NoSuchElementException e){
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }

    }
}