package attendance.domain;

import attendance.utils.DateUtil;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

public enum SchoolTime {
    MON("월",LocalTime.of(13,00),LocalTime.of(18,00)),
    TUE("화",LocalTime.of(10,00),LocalTime.of(18,00)),
    WED("수",LocalTime.of(10,00),LocalTime.of(18,00)),
    THU("목",LocalTime.of(10,00),LocalTime.of(18,00)),
    FRI("금",LocalTime.of(10,00),LocalTime.of(18,00)),
    SAT("토",null,null),
    SUN("일",null,null);

    private final String name;
    private final LocalTime start;
    private final LocalTime end;


    SchoolTime(String name, LocalTime start, LocalTime end) {
        this.name = name;
        this.start=start;
        this.end=end;
    }

    public static SchoolTime from(LocalDateTime localDateTime){
        for (SchoolTime school: SchoolTime.values()){
            if (school.name.equals(localDateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW,Locale.KOREAN))){
                return school;
            }
        }
        throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
    }

    public String getName() {
        return name;
    }
    public static final DateTimeFormatter DATE_COMPACT = DateTimeFormatter.ofPattern("HH:mm");

    public void validateWeekDay(LocalDateTime now) {
        if (this==SAT || this==SUN){
            throw new IllegalArgumentException(String.format("[ERROR] %s월 %s일 %s요일은 등교일이 아닙니다.",
                    now.getMonthValue(),now.getDayOfMonth(),now.getDayOfWeek().getDisplayName(TextStyle.NARROW,Locale.KOREAN)));
        }
    }

    public void validateSchoolTime(LocalTime now) {
        if (now.isBefore(LocalTime.parse("08:00",DATE_COMPACT)) || now.isAfter(LocalTime.parse("23:00",DATE_COMPACT))){
            throw new IllegalArgumentException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
        }
    }

    public String calculateStatus(LocalDateTime now) {
        validateWeekDay(now);

        if (now.toLocalTime().isAfter(start.plusMinutes(30))){
            return "결석";
        }

        if (now.toLocalTime().isAfter(start.plusMinutes(5))){
            return "지각";
        }

        return "출석";
    }


}
