package attendance.command;

public class Quit implements Command {
    public Quit() {
    }

    @Override
    public void execute() {
        quit();
    }

    public void quit() {
        return;
    }
}
