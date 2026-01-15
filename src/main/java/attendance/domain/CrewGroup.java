package attendance.domain;

import attendance.service.WarningDto;

import java.time.LocalDate;
import java.util.*;

public class CrewGroup {
    private final List<Crew> crews = new ArrayList<>();

    public void add(Crew crew) {
        if (!crews.contains(crew)){
            crews.add(crew);
        }
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
            crew.addOmittedHistory(date);
            crew.updateHistory();
        }
    }

    public WarningDto getWarning() {
        List<WarningDto.Warn> warns = new ArrayList<>();
        for (Crew crew : crews) {
            AttendanceSummary summary = crew.getSummary();
            Warning warning = Warning.of(summary.getLate(), summary.getAbsent());

            if (!warning.equals(Warning.NONE)){
                warns.add(new WarningDto.Warn(crew.getName(),summary.getLate(), summary.getAbsent(), warning.getName()));
            }

        }
        return new WarningDto(warns);
    }

}
