package attendance.service;

import java.util.List;

public class WarningDto {
    private final List<Warn> warns;

    public WarningDto(List<Warn> warns) {
        this.warns = warns;
    }

    public List<Warn> getWarns() {
        return warns;
    }

    public static class Warn{
        private final String name;
        private final int absent;
        private final int late;
        private final String status;

        public Warn(String name, int absent, int late, String status) {
            this.name = name;
            this.absent = absent;
            this.late = late;
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public int getAbsent() {
            return absent;
        }

        public int getLate() {
            return late;
        }

        public String getStatus() {
            return status;
        }
    }
}
