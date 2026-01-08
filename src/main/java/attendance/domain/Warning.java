package attendance.domain;

public enum Warning {
    FIRE("제적",6),
    MEETING("면담",3),
    WARN("경고",2),
    NONE("없음",0);

    private final String name;
    private final int count;

    Warning(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public Warning of(int late, int absent){
        // TODO: 지각, 결석 변환 계산하기
        for (Warning warning:Warning.values()){
            if (warning.count<=absent){
                return warning;
            }
        }
        throw new IllegalArgumentException("[ERROR] 0 이상의 숫자를 입력해야합니다.");
    }
}
