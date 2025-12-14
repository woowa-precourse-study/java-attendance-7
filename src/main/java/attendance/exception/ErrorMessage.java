package attendance.exception;

public enum ErrorMessage {
    INVALID_FORMAT("잘못된 형식을 입력하였습니다."),
    INVALID_NICKNAME("등록되지 않은 닉네임입니다."),
    INVALID_AT_HOLIDAY("12월 14일 토요일은 등교일이 아닙니다."),
    INVALID_MODIFY("아직 수정할 수 없습니다."),
    INVALID_TIME("캠퍼스 운영 시간에만 출석이 가능합니다."),
    ALREADY_PATICIPATE("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해주세요.");

    private final String message;
    private static final String prefix_message = "[ERROR] ";

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return prefix_message + message;
    }
}
