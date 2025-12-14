package attendance.util;

public final class Validator {
    private Validator() {
    }

    public static void validateNumber(String target) {
        try {
            Integer.parseInt(target);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("잘못된 형식을 입력하였습니다.");
        }
    }
}
