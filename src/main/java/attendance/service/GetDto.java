package attendance.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class GetDto {
    private final List<Attendance> attendances;
    private final Status status;
    private final String warning;

    public GetDto(List<Attendance> attendances,Status status,String warning) {
        this.attendances = attendances;
        this.status=status;
        this.warning=warning;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public Status getStatus() {
        return status;
    }

    public String getWarning() {
        return warning;
    }

    public static class Attendance{
        private final LocalDate date;
        private final LocalTime time;
        private final String status;

        public Attendance(LocalDate date,LocalTime time, String status) {
            this.date = date;
            this.time = time;
            this.status = status;
        }

        public LocalDate getDate() {
            return date;
        }

        public LocalTime getTime() {
            return time;
        }

        public String getStatus() {
            return status;
        }
    }

    public static class Status{
        private final int attend;
        private final int late;
        private final int absent;

        public Status(int attend, int late, int absent) {
            this.attend = attend;
            this.late = late;
            this.absent = absent;
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
}
