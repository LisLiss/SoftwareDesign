import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventsStatisticImpl implements EventsStatistic {
    private Clock clock;
    private final Map<String, List<Instant>> eventsMap = new HashMap<>();

    EventsStatisticImpl(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void incEvent(String name) {
        eventsMap.putIfAbsent(name, new ArrayList<>());
        eventsMap.get(name).add(clock.now());
    }

    private Map<String, List<Instant>> getStatisticByOneHour() {
        Instant timeForStatistic = clock.now().minus(Duration.ofHours(1));
        Map<String, List<Instant>> answer = new HashMap<>();
        for (Map.Entry<String, List<Instant>> i : eventsMap.entrySet()) {
            answer.put(i.getKey(), i.getValue().stream().filter(x -> x.isAfter(timeForStatistic))
                    .collect(Collectors.toList()));
        }
        return answer;
    }

    @Override
    public double getEventStatisticByName(String name) {
        return getStatisticByOneHour().getOrDefault(name, List.of()).size() / 60.0;
    }

    @Override
    public Map<String, Double> getAllEventStatistic() {
        Map<String, Double> answer = new HashMap<>();
        for (Map.Entry<String, List<Instant>> i : getStatisticByOneHour().entrySet()) {
            answer.put(i.getKey(), i.getValue().size() / 60.0);
        }
        return answer;
    }

    @Override
    public void printStatistic() {
        getAllEventStatistic().forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
