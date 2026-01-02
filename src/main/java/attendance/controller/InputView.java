package attendance.controller;

import attendance.exception.Validator;
import attendance.utils.DateUtil;
import camp.nextstep.edu.missionutils.Console;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

public class InputView {

    public String readMessage(LocalDateTime today) {
        System.out.println("오늘은 " + DateUtil.getFulldate(today) + "입니다. 기능을 선택해 주세요.");
        System.out.println("""
                1. 출석확인
                2. 출석 수정
                3. 크루별 출석 기록 확인
                4. 제적 위험자 확인
                Q. 종료
                """);
        String input = readInput(List.of(
                Validator::validateNotBlank,
                Validator::validateFunction
        ));
        return input;
    }

    public String readNickname() {
        System.out.println("닉네임을 입력해주세요");
        String input = readInput(List.of(
                Validator::validateNotBlank
        ));
        return input;
    }

    public String readSchoolTime() {
        System.out.println("등교 시간을 입력해주세요");
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