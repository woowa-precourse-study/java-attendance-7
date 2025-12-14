package attendance.domain.vo;

public enum Type {
    PARTICIPATE("출석"),
    LATE("지각"),
    ABSENT("결석"),
    NONE("해당 없음");

    private final String korean;

    Type(String korean){
        this.korean=korean;
    }

    public String getKorean(){
        return korean;
    }

}
