package attendance.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AttendanceSheetTest {
    AttendanceSheet sheet = new AttendanceSheet(new ArrayList<>());

    @Test
    void 새로운_출석_추가() {
        //given
        String name = "이든";

        LocalDateTime time = LocalDateTime.of(2024, 12, 9, 9, 59);
        //when
        Attendance attendance = sheet.attendBy(name, time);
        //then
        Assertions.assertThat(attendance)
                .extracting(Attendance::getName)
                .isEqualTo(name);
    }

    @Test
    void 주말은_출석_불가() {
        //given
        String name = "이든";

        LocalDateTime time = LocalDateTime.of(2024, 12, 14, 9, 59);
        //when
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> sheet.attendBy(name, time));
    }

    @Test
    void 중복_출석시_예외를_발생시킨다() {
        String name = "이든";

        LocalDateTime time = LocalDateTime.of(2024, 12, 9, 9, 59);
        //when
        Attendance attendance = sheet.attendBy(name, time);
        //then
        assertThatIllegalArgumentException()
                .isThrownBy(() -> sheet.attendBy(name, time))
                .withMessageContaining("이미 출석을 확인하였습니다.");
    }
}
