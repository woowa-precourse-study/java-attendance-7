package attendance.controller;

import attendance.exception.Validator;
import attendance.utils.Parser;
import camp.nextstep.edu.missionutils.Console;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class InputView {

    public String readFunction(LocalDate date) {
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MM월 dd일 E요일", Locale.KOREAN);
        System.out.printf("오늘은 %s입니다. 기능을 선택해 주세요.\n", date.format(f1));
        System.out.println("""
                1. 출석 확인
                2. 출석 수정
                3. 크루별 출석 기록 확인
                4. 제적 위험자 확인
                Q. 종료
                """);
        String input = Console.readLine().trim();
        Validator.validateChoice(input);
        return input;
    }

    public String readNickname() {
        System.out.println("닉네임을 입력해 주세요.");
        String input = Console.readLine().trim();
        return input;
    }

    public LocalTime readAttendanceTime() {
        System.out.println("등교 시간을 입력해주세요.");
        String input = Console.readLine().trim();

        List<String> times = Parser.splitBy(input, ":");
        Validator.validateParsedSize(times, 2);

        LocalTime time = Validator.validateTime(times.get(0), times.get(1));
        return time;
    }

    public String readModifyAttendanceNickname() {
        System.out.println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.");
        String input = Console.readLine().trim();
        return input;
    }

    public int readModifyAttendanceDate() {
        System.out.println("수정하려는 날짜(일)을 입력해 주세요.");
        String input = Console.readLine().trim();
        Validator.validateRange(input);
        return Integer.parseInt(input);
    }

    public LocalTime readModifyAttendanceTime() {
        System.out.println("언제로 변경하겠습니까?");
        String input = Console.readLine().trim();

        List<String> times = Parser.splitBy(input, ":");
        Validator.validateParsedSize(times, 2);

        LocalTime time = Validator.validateTime(times.get(0), times.get(1));
        return time;
    }

}