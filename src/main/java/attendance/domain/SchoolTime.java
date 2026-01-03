package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class SchoolTime {
    private final DayOfWeek dayOfWeek;
    private final LocalTime start;

    private final LocalTime end;

    private SchoolTime(DayOfWeek dayOfWeek, LocalTime start, LocalTime end) {
        this.dayOfWeek = dayOfWeek;
        this.start = start;
        this.end = end;
    }

    public static SchoolTime open(DayOfWeek dayOfWeek, LocalTime start, LocalTime end){
        return new SchoolTime(dayOfWeek,start,end);
    }

    public static SchoolTime closed(DayOfWeek dayOfWeek){
        return new SchoolTime(dayOfWeek,null,null);
    }

    public boolean isCampusTime(LocalTime localTime){
        return !(localTime.isBefore(LocalTime.of(8,0)) || localTime.isAfter(LocalTime.of(23,0)));
    }

    public boolean isSchoolTime(){
        return start!=null && end!=null;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

}
