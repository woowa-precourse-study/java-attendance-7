package attendance.exception;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface Validator {
    void validate(String input);


    static void validateNotBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 빈 값은 입력할 수 없습니다.");
        }
    }

    static void validateChoice(String input) {
        if (!Set.of("1","2","3","4","Q").contains(input)) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    static int validateIsNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    static LocalTime validateTime(String hour, String minute) {
        int h=Validator.validateIsNumber(hour);
        int m=Validator.validateIsNumber(minute);

        try {
            return LocalTime.of(h, m);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }


    static void validateParsedSize(List<String> inputs, int size) {
        if (inputs.size()!=size) {
            throw new IllegalArgumentException("[ERROR] 파싱한 개수가 다릅니다.");
        }
    }

    static void validateRange(String input) {
        int min = 1;
        int max = 31;
        int value = validateIsNumber(input);
        if (value < min || value > max) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

}
