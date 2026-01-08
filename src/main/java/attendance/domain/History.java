package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class History {
    private final LocalDate date;
    private LocalTime time;
    private final SchoolTime schoolTime;
    private String status;


    public History(LocalDate date, LocalTime time, SchoolTime schoolTime, String status) {
        this.date = date;
        this.time = time;
        this.schoolTime = schoolTime;
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void modifyTime(LocalTime afterTime){
        time=afterTime;
        status=schoolTime.calculateStatus(afterTime);
    }

    public LocalTime getTime() {
        return time;
    }

    public SchoolTime getSchoolTime() {
        return schoolTime;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(date, history.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }
}
