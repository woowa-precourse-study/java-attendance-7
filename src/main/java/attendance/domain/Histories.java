package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Histories {
    private final List<History> histories = new ArrayList<>();
    private final Warning warning = Warning.NONE;

    public void add(History history) {
        histories.add(history);
    }


    public History findByDate(LocalDate date) {
        Optional<History> history = histories.stream()
                .filter(a -> a.getDate().equals(date))
                .findFirst();

        return history.orElseThrow(
                () -> new IllegalArgumentException("[ERROR] 해당 기록이 존재하지 않습니다.")
        );
    }

    public void modify(LocalDate date, LocalTime time) {
        History history = findByDate(date);
        history.modifyTime(time);
    }

    public void addIfOmitted(LocalDate date) {
        try {
            findByDate(date);
        } catch (IllegalArgumentException ignored) {
            SchoolTime schoolTime = SchoolTime.of(date);
            if (schoolTime.isSchoolDay(date)) {
                History history = new History(date, null, SchoolTime.of(date), "결석");
                histories.add(history);
            }
        }
    }

    public List<History> getAllHistories() {
        return histories.stream()
                .sorted(Comparator.comparing(History::getDate))
                .collect(Collectors.toList());
    }

    public Map<String, Integer> getAllStatus() {
        return histories.stream()
                .filter(history -> history.getDate().isBefore(DateTimes.now().toLocalDate()))
                .collect(Collectors.groupingBy(
                        History::getStatus,
                        Collectors.summingInt(r -> 1)
                ));
    }

}
