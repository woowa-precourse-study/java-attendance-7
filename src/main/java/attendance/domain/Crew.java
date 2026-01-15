package attendance.domain;

import attendance.service.ModifyDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Crew {
    private final String name;
    private final Histories histories = new Histories();

    public Crew(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void updateHistory(){
        histories.updateSummary();
    }

    public AttendanceSummary getSummary(){
        return histories.getSummary();
    }

    public void addHistory(History history) {
        histories.add(history);
    }
    public void addOmittedHistory(LocalDate date){
        histories.addIfOmitted(date);
    }

    public List<History> getHistories() {
        return histories.getAllHistories();
    }

    public Map<String, Integer> getAllStatus() {
        return histories.getAllStatus();
    }

    public ModifyDto.After modifyHistory(LocalDate date, LocalTime time) {
        histories.modify(date, time);
        History history = histories.findByDate(date);
        return new ModifyDto.After(history.getTime(), history.getStatus());
    }

    public ModifyDto.Before getDateAndStatus(LocalDate date) {
        History history = histories.findByDate(date);

        return new ModifyDto.Before(history.getDate(), history.getTime(), history.getStatus());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crew crew = (Crew) o;
        return Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
