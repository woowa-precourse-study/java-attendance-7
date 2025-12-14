package attendance;

import attendance.controller.AttendanceController;
import attendance.factory.ApplicationFactory;

public class Application {
    public static void main(String[] args) {
        ApplicationFactory application = new ApplicationFactory();
        AttendanceController controller = application.controller();

        controller.run();
    }
}
