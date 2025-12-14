package attendance.domain.vo;

public enum Punishment {
    WARNING("경고"),
    INTERVIEW("면답"),
    FIRE("제적");

    private final String korean;

    Punishment(String korean){
        this.korean=korean;
    }

    public String getKorean(){
        return korean;
    }
}
