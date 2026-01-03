package attendance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Crew {

    private final String name;
    private final AttendanceBook attendanceBook = new AttendanceBook();

    public Crew(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public AttendanceBook getAttendanceBook() {
        return attendanceBook;
    }

    public void addAttendance(LocalDateTime localDateTime){
        attendanceBook.add(localDateTime);
    }

    public Attendance findAttendance(LocalDateTime localDateTime) {
        return attendanceBook.getAttendance(localDateTime);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crew crew = (Crew) o;
        return Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }




}
