package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Attendance {

    private final LocalDate localDate;
    private final LocalTime localTime;
    private final Status status;

    public Attendance(LocalDate localDate, LocalTime localTime, SchoolTime schoolTime){
        this.localDate=localDate;
        this.localTime=localTime;
        this.status=AttendancePolicy.decideStatus(localDate,localTime,schoolTime);
    }


    public LocalDate getLocalDate() {
        return localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return Objects.equals(localDate, that.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localDate);
    }


    public enum Status{
        ATTENDANCE("출석"),ABSENT("결석"),LATE("지각");
        private final String name;
        Status(String name){
            this.name=name;
        }
        public String getName() {
            return name;
        }

    }

}
