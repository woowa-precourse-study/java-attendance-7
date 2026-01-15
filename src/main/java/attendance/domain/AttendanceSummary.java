package attendance.domain;

public class AttendanceSummary {
    private int attend = 0;
    private int late = 0;
    private int absent = 0;

    public void update(int newAttend, int newLate, int newAbsent) {
        attend = newAttend;
        late = newLate;
        absent = newAbsent;
    }

    public int getAttend() {
        return attend;
    }

    public int getLate() {
        return late;
    }

    public int getAbsent() {
        return absent;
    }
}
