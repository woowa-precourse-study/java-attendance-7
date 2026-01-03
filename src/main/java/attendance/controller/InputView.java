package attendance.controller;

import attendance.exception.Validator;
import attendance.utils.DateUtil;
import camp.nextstep.edu.missionutils.Console;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

public class InputView {

    public String readMessage(LocalDateTime today) {
        DateTimeFormatter f1=DateTimeFormatter.ofPattern("MM월 dd일 E요일", Locale.KOREAN);
        System.out.println("오늘은 " + today.format(f1) + "입니다. 기능을 선택해 주세요.");
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
                Validator::validateNotBlank,
                Validator::validateTime
        ));
        return input;
    }

    public String readModifyAttendanceName() {
        System.out.println("출석을 수정하려는 크루의 닉네임을 입력해주세요");
        String input = readInput(List.of(
                Validator::validateNotBlank
        ));
        return input;
    }

    public int readModifyAttendanceDate() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
        String input = readInput(List.of(
                Validator::validateNotBlank,
                Validator::validateRange

        ));
        return Integer.parseInt(input);
    }

    public String readModifyAttendanceTime() {
        System.out.println("언제로 변경하겠습니까?");
        String input = readInput(List.of(
                Validator::validateNotBlank,
                Validator::validateTime
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