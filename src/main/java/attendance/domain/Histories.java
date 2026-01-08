package attendance.domain;

import java.util.ArrayList;
import java.util.List;

public class Histories {
    private final List<History> histories = new ArrayList<>();
    private final Warning warning=Warning.NONE;

    public void add(History history){
        histories.add(history);
    }

}
