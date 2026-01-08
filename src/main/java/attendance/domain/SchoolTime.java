package attendance.domain;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public enum SchoolTime {
    MONDAY("월", 1, LocalTime.of(13,0),LocalTime.of(18,0),LocalTime.of(8,0),LocalTime.of(23,0)),
    TUESDAY("화", 2,LocalTime.of(10,0),LocalTime.of(18,0),LocalTime.of(8,0),LocalTime.of(23,0)),
    WEDNESDAY("수", 3,LocalTime.of(10,0),LocalTime.of(18,0),LocalTime.of(8,0),LocalTime.of(23,0)),
    THURSDAY("목", 4,LocalTime.of(10,0),LocalTime.of(18,0),LocalTime.of(8,0),LocalTime.of(23,0)),
    FRIDAY("금", 5,LocalTime.of(10,0),LocalTime.of(18,0),LocalTime.of(8,0),LocalTime.of(23,0)),
    SATURDAY("토", 6,null,null,LocalTime.of(8,0),LocalTime.of(23,0)),
    SUNDAY("일", 7,null,null,LocalTime.of(8,0),LocalTime.of(23,0));

    private final String korName;
    private final int number;
    private final LocalTime edu_start;
    private final LocalTime edu_end;
    private final LocalTime school_start;
    private final LocalTime school_end;

    SchoolTime(String korName, int number, LocalTime edu_start, LocalTime edu_end, LocalTime school_start, LocalTime school_end) {
        this.korName = korName;
        this.number = number;
        this.edu_start = edu_start;
        this.edu_end = edu_end;
        this.school_start = school_start;
        this.school_end = school_end;
    }

    public boolean isSchoolDay(LocalDate date){
        if (date.equals(LocalDate.of(2024,12,25))){
            return false;
        }

        return (!(this.number == DayOfWeek.SATURDAY.getValue() || this.number == DayOfWeek.SUNDAY.getValue()));
    }

    public boolean isSchoolTime(LocalTime time){
        return (!(time.isBefore(school_start) || time.isAfter(school_end)));
    }

    public boolean isEduTime(LocalTime time){
        return time.isAfter(this.edu_start) || time.isBefore(this.edu_end);
    }

    public String getKorName() {
        return korName;
    }

    public String calculateStatus(LocalTime time){
        if (time.isAfter(this.edu_start.plusMinutes(30))){
            return "결석";
        }
        if (time.isAfter(this.edu_start.plusMinutes(5))){
            return "지각";
        }
        return "출석";
    }


    public static SchoolTime of(LocalDate localDate) {
        for (SchoolTime  schoolTime  : SchoolTime .values()) {
            if (schoolTime.korName.equals(localDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN))) {
                return schoolTime ;
            }
        }
        throw new IllegalArgumentException("해당 요일이 존재하지 않습니다.");
    }

}
