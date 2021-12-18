import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;


public class EventsStatisticTests {
    private SettableClock clock;
    private EventsStatistic eventsStatistic;

    @Before
    public void before() {
        clock = new SettableClock(Instant.now());
        eventsStatistic = new EventsStatisticImpl(clock);
    }

    @Test
    public void emptyTest() {
        Assert.assertEquals(0, eventsStatistic.getAllEventStatistic().size());
    }

    @Test
    public void unknownNameTest() {
        Assert.assertEquals(0, eventsStatistic.getEventStatisticByName("0"), 1e-10);
    }

    @Test
    public void oneNameTest() {
        eventsStatistic.incEvent("cat");
        eventsStatistic.incEvent("cat");
        eventsStatistic.incEvent("cat");
        eventsStatistic.incEvent("cat");
        eventsStatistic.incEvent("cat");
        Assert.assertEquals(5 / 60.0, eventsStatistic.getEventStatisticByName("cat"), 1e-10);
    }

    @Test
    public void manyNameTest() {
        eventsStatistic.incEvent("cat");
        eventsStatistic.incEvent("dog");
        eventsStatistic.incEvent("bird");
        eventsStatistic.incEvent("cat");
        Assert.assertEquals(2 / 60.0, eventsStatistic.getEventStatisticByName("cat"), 1e-10);
        Assert.assertEquals(1 / 60.0, eventsStatistic.getEventStatisticByName("dog"), 1e-10);
        Assert.assertEquals(1 / 60.0, eventsStatistic.getEventStatisticByName("bird"), 1e-10);
    }

    @Test
    public void oldOneNameTest() {
        eventsStatistic.incEvent("cat");
        eventsStatistic.incEvent("cat");
        clock.addHour();
        Assert.assertEquals(0 / 60.0, eventsStatistic.getEventStatisticByName("cat"), 1e-10);
    }

    @Test
    public void oldManyNameTest() {
        eventsStatistic.incEvent("cat");
        eventsStatistic.incEvent("cat");
        eventsStatistic.incEvent("dog");
        eventsStatistic.incEvent("bird");
        eventsStatistic.incEvent("dog");
        eventsStatistic.incEvent("bird");
        clock.addHour();
        eventsStatistic.incEvent("dog");
        Assert.assertEquals(1 / 60.0, eventsStatistic.getEventStatisticByName("dog"), 1e-10);
    }

    @Test
    public void multiTimeTest() {
        eventsStatistic.incEvent("cat");
        clock.addTimeMin(Duration.ofMinutes(10));
        Assert.assertEquals(1 / 60.0, eventsStatistic.getEventStatisticByName("cat"), 1e-10);
        eventsStatistic.incEvent("cat");
        eventsStatistic.incEvent("dog");
        clock.addTimeMin(Duration.ofMinutes(30));
        Assert.assertEquals(2 / 60.0, eventsStatistic.getEventStatisticByName("cat"), 1e-10);
        Assert.assertEquals(1 / 60.0, eventsStatistic.getEventStatisticByName("dog"), 1e-10);
        clock.addTimeMin(Duration.ofMinutes(20));
        Assert.assertEquals(1 / 60.0, eventsStatistic.getEventStatisticByName("cat"), 1e-10);
        Assert.assertEquals(1 / 60.0, eventsStatistic.getEventStatisticByName("dog"), 1e-10);
    }
}
