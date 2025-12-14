package attendance.factory;

import attendance.domain.Attendance;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ApplicationFactoryTest {

    ApplicationFactory factory = new ApplicationFactory();

    @Test
    void 초기_출석_데이터() {
        //given
        List<Attendance> attendances = factory.defaultAttendances();
        //when
        //then
        Assertions.assertThat(attendances)
                .hasSize(41);
    }
}
