package attendance.controller;

import attendance.domain.vo.Attendance;
import camp.nextstep.edu.missionutils.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InputView {
    Map<String,List<Attendance>> readAttendanceCsv(){
        Map<String,List<Attendance>> attendances= new HashMap<>();
        try{
            BufferedReader br = Files.newBufferedReader(Path.of("src/main/resources/attendances.csv"));
            br.readLine(); // header skip
            String line;
            SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            while((line=br.readLine())!=null){
                String[] cols = line.split(",");
                if (!attendances.containsKey(cols[0])){
                    attendances.put(cols[0],new ArrayList<Attendance>());
                }
                Attendance attendance=Attendance.of(f1.parse(cols[1]));
                attendances.get(cols[0]).add(attendance);
            }
        } catch (IOException | ParseException e) {
            throw new IllegalStateException("파일을 읽는데 오류가 발생했습니다.");
        }
        return attendances;
    }

    int readSelectedFunction(LocalDateTime today){
        String parsedNow = today.format(DateTimeFormatter.ofPattern("MM월 dd일 E요일",Locale.KOREAN));
        System.out.println(String.format("오늘은 %s입니다. 기능을 선택해주세요",parsedNow));
        return Integer.parseInt(Console.readLine());
    }

    String readNickname(){
        return Console.readLine();
    }




}
