package attendance.domain;

public enum AttendanceState {
    ATTENDANCE("출석", 0),
    LATE("지각", 1),
    ABSENT("결석", 2);

    private final String name;
    private final int value;

    public String getName() {
        return name;
    }

    AttendanceState(String name, int value) {
        this.name = name;
        this.value = value;
    }
}
