package attendance.factory;

import attendance.controller.AttendanceController;
import attendance.controller.Command;
import attendance.controller.command.AttendCommand;
import attendance.controller.command.QuitCommand;
import attendance.domain.Attendance;
import attendance.domain.AttendanceSheet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationFactory {
    public AttendanceController controller() {
        return new AttendanceController(attendanceSheet(), command());
    }

    public AttendanceSheet attendanceSheet() {
        return new AttendanceSheet(defaultAttendances());
    }

    public List<Attendance> defaultAttendances() {
        List<Attendance> attendances = new ArrayList<>();

        File file = new File("src/main/resources/attendances.csv");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine();
            readFile(reader, attendances);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return attendances;
    }

    private void readFile(BufferedReader reader, List<Attendance> attendances) throws IOException {
        while (reader.ready()) {
            String line = reader.readLine();
            List<String> studentInfo = List.of(line.split(","));

            String name = studentInfo.getFirst();
            String dateTime = studentInfo.getLast();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime time = LocalDateTime.parse(dateTime, formatter);

            attendances.add(Attendance.of(name, time));
        }
    }

    public Map<String, Command> command() {
        return Map.of(
                "1", new AttendCommand(),
                "Q", new QuitCommand()
        );
    }
}
