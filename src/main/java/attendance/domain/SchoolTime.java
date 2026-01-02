package attendance.domain;

import attendance.utils.DateUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public enum SchoolTime {
    MON("월요일","13:00","18:00"),
    TUE("화요일","10:00","18:00"),
    WED("수요일","13:00","18:00"),
    THU("목요일","13:00","18:00"),
    FRI("금요일","13:00","18:00"),
    SAT("토요일","NONE","NONE"),
    SUN("일요일","NONE","NONE");

    private final String name;
    private final String start;
    private final String end;


    SchoolTime(String name, String start, String end) {
        this.name = name;
        this.start=start;
        this.end=end;
    }

    public static SchoolTime fromDayofWeek(String dayOfWeek){
        for (SchoolTime school: SchoolTime.values()){
            if (school.name.equals(dayOfWeek)){
                return school;
            }
        }
        throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
    }

    public String getName() {
        return name;
    }
    public static final DateTimeFormatter DATE_COMPACT = DateTimeFormatter.ofPattern("HH:mm");

    public void validateSchoolTime(LocalDateTime now) {
        if (DateUtil.getDayOfWeek(now).equals(SAT.name) || DateUtil.getDayOfWeek(now).equals(SUN.name)){
            throw new IllegalArgumentException(String.format("[ERROR] %s은 등교일이 아닙니다.",DateUtil.getFulldate(now)));
        }
        if (now.isBefore(DateUtil.parseTime("08:00",DATE_COMPACT)) && now.isAfter(DateUtil.parseTime("23:00",DATE_COMPACT))){
            throw new IllegalArgumentException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
        }
    }

    public String calculateStatus(LocalDateTime now) {
        if (now.isAfter(DateUtil.addMinutes(DateUtil.parseTime(start,DATE_COMPACT),30))){
            return "결석";
        }

        if (now.isAfter(DateUtil.addMinutes(DateUtil.parseTime(start,DATE_COMPACT),5))){
            return "지각";
        }

        return "출석";
    }


//    public String getStart() {
//        return start;
//    }
//
//    public String getEnd() {
//        return end;
//    }


}
