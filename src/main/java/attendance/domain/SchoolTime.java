package attendance.domain;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public enum SchoolTime {
    MON("월", 1, LocalTime.of(13,0),LocalTime.of(18,0),LocalTime.of(8,0),LocalTime.of(23,0)),
    TUE("화", 2,LocalTime.of(10,0),LocalTime.of(18,0),LocalTime.of(8,0),LocalTime.of(23,0)),
    WED("수", 3,LocalTime.of(10,0),LocalTime.of(18,0),LocalTime.of(8,0),LocalTime.of(23,0)),
    THU("목", 4,LocalTime.of(10,0),LocalTime.of(18,0),LocalTime.of(8,0),LocalTime.of(23,0)),
    FRI("금", 5,LocalTime.of(10,0),LocalTime.of(18,0),LocalTime.of(8,0),LocalTime.of(23,0)),
    SAT("토", 6,null,null,LocalTime.of(8,0),LocalTime.of(23,0)),
    SUN("일", 7,null,null,LocalTime.of(8,0),LocalTime.of(23,0));

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

    public boolean isSchoolTime(LocalDate date,LocalTime time){
        if (date==LocalDate.of(2024,12,25)){
            return false;
        }

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek== DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY){
            return false;
        }

        if (time.isBefore(school_start) || time.isAfter(school_end)){
            return false;
        }
        return true;
    }

    public boolean isEduTime(LocalDate date,LocalTime time){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek==DayOfWeek.MONDAY){
            return time.isAfter(SchoolTime.MON.edu_start) || time.isBefore(SchoolTime.MON.edu_end);
        }
        return time.isAfter(SchoolTime.TUE.edu_start) || time.isBefore(SchoolTime.TUE.edu_end);
    }



    public static SchoolTime of(String name) {
        for (SchoolTime  SchoolTime  : SchoolTime .values()) {
            if (SchoolTime .korName.equals(name)) {
                return SchoolTime ;
            }
        }
        throw new IllegalArgumentException("해당 요일이 존재하지 않습니다.");
    }

    public String getKorName() {
        return korName;
    }

    public boolean isWeekend(){
        return this == SAT || this == SchoolTime .SUN;
    }

    public SchoolTime  getNext() {
        return SchoolTime .values()[this.number % 7];
    }

}
