package attendance.controller;

import attendance.domain.vo.Attendance;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class InputView {

    List<Attendance> readAttendanceCsv(){
        List<Attendance> attendances=new ArrayList<>();
        try{
            BufferedReader br = Files.newBufferedReader(Path.of("src/main/resources/attendances.csv"));
            br.readLine(); // header skip
            String line;
            SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            while((line=br.readLine())!=null){
                String[] cols = line.split(",");
                Attendance attendance=Attendance.from(
                        cols[0],
                        f1.parse(cols[1])
                );
                attendances.add(attendance);
            }
        } catch (IOException | ParseException e) {
            throw new IllegalStateException("파일을 읽는데 오류가 발생했습니다.");
        }

        return attendances;
    }


}
