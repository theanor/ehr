package be.monolith.ehr.parties.text;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;

import be.monolith.ehr.parties.dto.Person;
import be.monolith.ehr.text.AbstractFormatter;

public class PersonAgeFormatter extends AbstractFormatter<Person> {

	private Clock clock;

	public PersonAgeFormatter() {
	}

	public PersonAgeFormatter(Clock clock) {
		this.clock = clock;
	}

	private Instant now() {
		if (clock == null)
			clock = Clock.systemDefaultZone();
		return clock.instant();
	}

	@Override
	public String format(Person person) { // TODO a more correct implementation (for months and years)

		Instant now = now();
		Instant birth = person.getBirthDate().getValue().toInstant();
		Duration duration = Duration.between(birth, now);
		long seconds = duration.getSeconds();
		if (seconds < 60) { // seconds
			return String.format("%d %s", duration.getSeconds(),
					be.monolith.ehr.value.Instant.Field.SECOND.shortLabel());
		} else if (seconds < 3600) { // minutes + seconds
			return String.format("%d %s, %d %s", duration.toMinutes(),
					be.monolith.ehr.value.Instant.Field.MINUTE.shortLabel(), duration.getSeconds() % 60,
					be.monolith.ehr.value.Instant.Field.SECOND.shortLabel());

		} else if (seconds < 24 * 3600) { // hours + minutes
			return String.format("%d %s, %d %s", duration.toHours(),
					be.monolith.ehr.value.Instant.Field.HOUR.shortLabel(), duration.toMinutes() % 60,
					be.monolith.ehr.value.Instant.Field.MINUTE.shortLabel());
		} else if (seconds < 24 * 3600 * 7) { // days + hours
			return String.format("%d %s, %d %s", duration.toDays(),
					be.monolith.ehr.value.Instant.Field.DAY.shortLabel(), duration.toHours() % 24,
					be.monolith.ehr.value.Instant.Field.HOUR.shortLabel());
		} else if (seconds < 24 * 3600 * 30) { // weeks + days
			return String.format("%d %s, %d %s", duration.toDays() / 7,
					be.monolith.ehr.value.Instant.Field.WEEK.shortLabel(), duration.toDays() % 7,
					be.monolith.ehr.value.Instant.Field.DAY.shortLabel());
		} else if (seconds < 24 * 3600 * 30 * 12) { // months and days
			return String.format("%d %s, %d %s", duration.toDays() / 30,
					be.monolith.ehr.value.Instant.Field.MONTH.shortLabel(), duration.toDays() % 30,
					be.monolith.ehr.value.Instant.Field.DAY.shortLabel());
		} else if (seconds < 24 * 3600 * 30 * 12 * 2) { // years and months
			return String.format("%d %s, %d %s", duration.toDays() / 365,
					be.monolith.ehr.value.Instant.Field.YEAR.shortLabel(), (duration.toDays() % 365) / 30,
					be.monolith.ehr.value.Instant.Field.MONTH.shortLabel());
		} else { // years
			return String.format("%d %s", duration.toDays() / 365,
					be.monolith.ehr.value.Instant.Field.YEAR.shortLabel());
		}

	}

}
