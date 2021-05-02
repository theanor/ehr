package be.monolith.ehr.text;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import be.monolith.ehr.value.Instant;
import be.monolith.ehr.value.Instant.Precision;

public class InstantFormatter extends AbstractFormatter<Instant> {

	@SuppressWarnings("serial")
	private static final Map<Precision, Formatter<Instant>> DEFAULT_FORMATS = Collections
			.unmodifiableMap(new HashMap<Precision, Formatter<Instant>>() {
				{
					put(Precision.DAY, new DatePatternFormat("yyyy-MM-dd"));
					put(Precision.MILLISECOND, new DatePatternFormat("yyyy-MM-dd HH:mm:ss.zzz"));
					put(Precision.HOUR, new DatePatternFormat("yyyy-MM-dd HH"));
					put(Precision.MINUTE, new DatePatternFormat("yyyy-MM-dd HH:mm"));
					put(Precision.MONTH, new DatePatternFormat("yyyy-MM"));
					put(Precision.SECOND, new DatePatternFormat("yyyy-MM-dd HH:mm:ss"));
					put(Precision.TIME_HOUR, new DatePatternFormat("HH"));
					put(Precision.TIME_MILLISECOND, new DatePatternFormat("HH:mm:ss.zzz"));
					put(Precision.TIME_MINUTE, new DatePatternFormat("HH:mm"));
					put(Precision.TIME_SECOND, new DatePatternFormat("HH:mm:ss"));
					put(Precision.UNKNOWN, new UnknownFormat());
					put(Precision.YEAR, new DatePatternFormat("yyyy"));
				}
			});

	private static class DatePatternFormat implements Formatter<Instant> {

		private Locale locale;
		private String pattern;

		public DatePatternFormat(String datePattern) {
			this(datePattern, Locale.getDefault(Locale.Category.FORMAT));
		}

		public DatePatternFormat(String datePattern, Locale locale) {
			this.locale = locale;
			this.pattern = datePattern;
		}

		@Override
		public String format(Instant object) {
			return new SimpleDateFormat(pattern, locale).format(object.getValue());
		}

	}

	/*
	 * private static class IndayFormat extends Format<Instant> {
	 * 
	 * private Map<TimeInDay, String> names = Collections.unmodifiableMap(new
	 * HashMap<TimeInDay, String>() { { put(TimeInDay.FASTING, "à jeun");
	 * 
	 * put(TimeInDay.BEFORE_BREAKFAST, "avant le petit-déjeuner");
	 * put(TimeInDay.DURING_BREAKFAST, " le matin en mangeant");
	 * put(TimeInDay.AFTER_BREAKFAST, "après le petit-déjeuner");
	 * 
	 * put(TimeInDay.AT_10AM, "à 10h");
	 * 
	 * put(TimeInDay.BEFORE_LUNCH, "avant le déjeuner"); put(TimeInDay.DURING_LUNCH,
	 * "le midi en mangeant"); put(TimeInDay.AFTER_LUNCH, "après le déjeuner");
	 * 
	 * put(TimeInDay.AT_4PM, "à 16h");
	 * 
	 * put(TimeInDay.BEFORE_DINNER, "avant le dîner"); put(TimeInDay.DURING_DINNER,
	 * "le soir en mangeant"); put(TimeInDay.AFTER_DINNER, "après le dîner");
	 * 
	 * put(TimeInDay.AT8PM, "à 20h");
	 * 
	 * put(TimeInDay.BEFORE_SLEEP, "avant de se coucher");
	 * 
	 * } });
	 * 
	 * @Override public Instant parse(String string) throws ParseException { for
	 * (Map.Entry<TimeInDay, String> entry : names.entrySet()) { if
	 * (entry.getValue().equalsIgnoreCase(string)) { return new
	 * Instant(entry.getKey()); } } throw new
	 * ParseException("not a time in the day", 0); }
	 * 
	 * @Override public String format(Instant object) { return
	 * names.get(Instant.TimeInDay.valueOf(object).name()); }
	 * 
	 * }
	 */
	private static class UnknownFormat implements Formatter<Instant> {

		private String unknownString = "?";

		public UnknownFormat() {
			super();
		}

		@Override
		public String format(Instant object) {
			if (object.getPrecision() == Precision.UNKNOWN) {
				return unknownString;
			} else
				throw new RuntimeException("supports only Precision.UNKNOWN");
		}

	}

	@Override
	public String format(Instant instant) {
		return DEFAULT_FORMATS.get(instant.getPrecision()).format(instant);
	}

}
