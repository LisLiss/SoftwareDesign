import java.time.Duration;
import java.time.Instant;

public class SettableClock implements Clock {
    Instant now;

    public SettableClock(Instant now) {
        this.now = now;
    }

    public void setNow(Instant now) {
        this.now = now;
    }

    public void addHour() {
        now = now.plus(Duration.ofHours(1));
    }

    public void addTimeMin(Duration minutes) {
        now = now.plus(minutes);
    }

    @Override
    public Instant now() {
        return now;
    }
}
