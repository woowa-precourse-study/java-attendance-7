package attendance.domain;

import attendance.controller.OutputView;
import attendance.service.GetDto;
import attendance.service.ModifyDto;
import attendance.service.WarningDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class CrewGroup {
    private final List<Crew> crews = new ArrayList<>();
    private final Map<Crew, Histories> histories = new LinkedHashMap<>();

    public void add(Crew crew) {
        if (!crews.contains(crew)){
            crews.add(crew);
        }
    }

    public void addHistory(Crew crew, History history) {
        getOrCreate(crew);
        histories.get(crew).add(history);
    }

    public Histories getOrCreate(Crew crew) {
        return histories.computeIfAbsent(
                crew,
                k -> new Histories()
        );
    }

    public ModifyDto.Before getDateAndStatus(Crew crew, LocalDate date) {
        Histories histories1 = getOrCreate(crew);
        History history = histories1.findByDate(date);

        return new ModifyDto.Before(history.getDate(), history.getTime(), history.getStatus());
    }

    public ModifyDto.After modifyHistory(Crew crew, LocalDate date, LocalTime time) {
        Histories histories1 = histories.get(crew);

        histories1.modify(date, time);
        History history = histories1.findByDate(date);
        return new ModifyDto.After(history.getTime(), history.getStatus());
    }

    public Crew findByName(String name) {
        Optional<Crew> crew = crews.stream()
                .filter(a -> a.getName().equals(name))
                .findFirst();

        return crew.orElseThrow(
                () -> new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.")
        );
    }

    public void addOmittedHistory(LocalDate date) {
        for (Crew crew : crews) {
            histories.get(crew).addIfOmitted(date);
        }
    }

    public List<History> getHistories(Crew crew) {
        Histories histories1 = histories.get(crew);
        return histories1.getAllHistories();
    }

    public Map<String, Integer> getAllStatus(Crew crew) {
        Histories histories1 = histories.get(crew);
        return histories1.getAllStatus();
    }

    public WarningDto getWarning() {
        List<WarningDto.Warn> warns = new ArrayList<>();
        for (Crew crew : crews) {
            Map<String, Integer> allStatus = getAllStatus(crew);
            int late = allStatus.getOrDefault("지각", 0);
            int absent = allStatus.getOrDefault("결석", 0);

            Warning warning = Warning.of(late, absent);

            if (!warning.equals(Warning.NONE)){
                warns.add(new WarningDto.Warn(crew.getName(),absent, late, warning.getName()));
            }

        }
        return new WarningDto(warns);
    }

}
