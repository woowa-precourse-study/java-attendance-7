package attendance.domain;

import java.util.*;

public class CrewGroup {
    private final List<Crew> crews = new ArrayList<>();
    private final Map<Crew,Histories> histories = new LinkedHashMap<>();

    public void add(Crew crew) {
        crews.add(crew);
    }

    public void addHistory(Crew crew, History history){
        getOrCreate(crew);
        histories.get(crew).add(history);
    }

    public Histories getOrCreate(Crew crew) {
        return histories.computeIfAbsent(
                crew,
                k -> new Histories()
        );
    }

    public Crew findByName(String name){
        Optional<Crew> crew = crews.stream()
                .filter(a -> a.getName().equals(name))
                .findFirst();

        return crew.orElseThrow(
                () -> new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.")
        );
    }

}
