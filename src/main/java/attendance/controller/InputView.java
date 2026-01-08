package attendance.controller;

import attendance.exception.Validator;
import camp.nextstep.edu.missionutils.Console;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class InputView {

    public String readFunction(LocalDate date) {
        DateTimeFormatter f1= DateTimeFormatter.ofPattern("MM월 dd일 E요일", Locale.KOREAN);
        System.out.printf("오늘은 %s입니다. 기능을 선택해 주세요.n",date.format(f1));
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

}