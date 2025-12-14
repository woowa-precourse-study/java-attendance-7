package attendance.controller;

import attendance.service.AttendanceService;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;

import static camp.nextstep.edu.missionutils.test.Assertions.assertNowTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

class AttendanceControllerTest extends NsTest {

    @Test
    void 출석_확인_기능_테스트() {
        assertNowTest(
                () -> {
                    runException("1", "짱수", "08:00");
                    assertThat(output())
//                            .contains("12월 13일 금요일 08:00 (출석)");
                            .doesNotContain("[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.");
                },

                LocalDate.of(2024, 12, 13).atStartOfDay()
        );
    }


    @Override
    protected void runMain() {
        AttendanceService attendanceService = new AttendanceService();
        AttendanceController attendanceController = new AttendanceController(attendanceService);
        try {
            attendanceController.startAttendance();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}