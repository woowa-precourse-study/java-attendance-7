package attendance.service;

import java.time.LocalDate;
import java.time.LocalTime;

public class ModifyDto {
    public static class Before{
        private final LocalDate date;
        private final LocalTime time;
        private final String status;

        public Before(LocalDate date,  LocalTime time, String status) {
            this.date = date;
            this.time = time;
            this.status = status;
        }

        public LocalDate getDate() {
            return date;
        }

        public String getStatus() {
            return status;
        }

        public LocalTime getTime() {
            return time;
        }
    }

    public static class After{
        private final LocalTime time;
        private final String status;

        public After(LocalTime time, String status) {
            this.time = time;
            this.status = status;
        }

        public LocalTime getTime() {
            return time;
        }

        public String getStatus() {
            return status;
        }
    }



}
