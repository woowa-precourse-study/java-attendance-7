package attendance.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CrewGroup {
    private final List<Crew> crewGroup=new ArrayList<>();

    public CrewGroup() {}

    public void addCrew(String name){
        crewGroup.add(new Crew(name));
    }

    public boolean deleteCrew(String name){
        return crewGroup.removeIf(crew -> Objects.equals(crew.getName(), name));
    }

    public boolean containCrew(String name){
        return crewGroup.stream()
                .anyMatch(crew -> Objects.equals(crew.getName(),name));
    }

    public Crew findByName(String name){
        Optional<Crew> crew = crewGroup.stream()
                .filter(a -> a.getName().equals(name))
                .findFirst();

        return crew.orElseThrow(
                () -> new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.")
        );
    }




}
