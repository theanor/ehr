package be.monolith.ehr.value;

import java.util.Calendar;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import com.fasterxml.jackson.annotation.JsonProperty;

import be.monolith.ehr.text.ClassMessages;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = false)
@Getter
public class Instant implements Comparable<Instant> {

	public static final Instant MIN_VALUE = new Instant(Precision.MILLISECOND, new Date(0));
	public static final Instant MAX_VALUE = new Instant(Precision.MILLISECOND, new Date(Long.MAX_VALUE));
	public static final Instant UNKNOWN = new Instant(Precision.UNKNOWN, new Date(0));

	@NotNull
	@XmlAttribute(required = true)
	@JsonProperty(required = true)
	private Precision precision;

	@XmlValue
	@JsonProperty(required = false)
	private Date value;

	public enum Precision {
		UNKNOWN, YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, SECOND, MILLISECOND, TIME_MILLISECOND, TIME_SECOND, TIME_MINUTE,
		TIME_HOUR;
	}

	public Instant() {
		this.precision = Precision.UNKNOWN;
		this.value = UNKNOWN.getValue();
	}

	public Instant(Precision precision, Date date) {
		this.precision = precision;
		this.value = trunc(date, precision);
	}

	public Instant(Precision precision, Date date, Instant source, Field... fields) {

		this.precision = precision;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		Calendar scal = source.calendar();
		for (Field field : fields) {
			int cf = field.toCalendar();
			cal.set(cf, scal.get(cf));
		}
		this.value = trunc(cal.getTime(), precision);

	}

	public boolean after(Instant instant) {
		return this.equals(UNKNOWN) || this.value.after(instant.getValue());
	}

	public boolean before(Instant instant) {
		return this.equals(UNKNOWN) || this.value.before(instant.getValue());
	}

	public Instant trunc(Precision precision) {
		return new Instant(precision, trunc(this.value, precision));
	}

	public int get(Field field) {
		return calendar().get(field.toCalendar());
	}

	public Instant add(Field field, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTime(this.getValue());
		c.add(field.toCalendar(), amount);
		return new Instant(this.precision, c.getTime());
	}

	private Calendar calendar() {
		Calendar result = Calendar.getInstance();
		result.setTime(this.value);
		return result;
	}

	private Date trunc(Date date, Precision precision) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		switch (precision) {
		case TIME_MILLISECOND:
			cal.set(Calendar.DAY_OF_YEAR, 0);
			cal.set(Calendar.YEAR, 0);
			break;
		case TIME_SECOND:
			cal.set(Calendar.DAY_OF_YEAR, 0);
			cal.set(Calendar.YEAR, 0);
			cal.set(Calendar.MILLISECOND, 0);
			break;
		case TIME_MINUTE:
			cal.set(Calendar.DAY_OF_YEAR, 0);
			cal.set(Calendar.YEAR, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			break;
		case TIME_HOUR:
			cal.set(Calendar.DAY_OF_YEAR, 0);
			cal.set(Calendar.YEAR, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			break;
		case DAY:
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			break;
		case HOUR:
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			break;
		case MINUTE:
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			break;
		case MILLISECOND:
			break;
		case WEEK:
			cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			break;
		case MONTH:
			cal.set(Calendar.DAY_OF_MONTH, 15);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			break;
		case SECOND:
			cal.set(Calendar.MILLISECOND, 0);
			break;
		case UNKNOWN:
			cal.setTime(new Date(0));
			break;
		case YEAR:
			cal.set(Calendar.DAY_OF_MONTH, 15);
			cal.set(Calendar.MONTH, 7);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			break;
		default:
			break;

		}
		return cal.getTime();
	}

	@Override
	public int compareTo(Instant o) {
		return this.getValue().compareTo(o.getValue());
	}

	public String toString() {
		return String.format("%s:%s", this.precision.toString(), this.value.toString());

	}

	public enum Field {

		YEAR, MONTH, WEEK, DAY, HOUR, MINUTE, SECOND, MILLISECOND;

		int toCalendar() {
			switch (this) {
			case DAY:
				return Calendar.DAY_OF_MONTH;
			case HOUR:
				return Calendar.HOUR_OF_DAY;
			case MILLISECOND:
				return Calendar.MILLISECOND;
			case MINUTE:
				return Calendar.MINUTE;
			case WEEK:
				return Calendar.WEEK_OF_YEAR;
			case MONTH:
				return Calendar.MONTH;
			case SECOND:
				return Calendar.SECOND;
			case YEAR:
				return Calendar.YEAR;
			default:
				return -1;
			}
		}

		Field fromCalendar(int c) {
			switch (c) {
			case Calendar.DAY_OF_MONTH:
				return DAY;
			case Calendar.HOUR_OF_DAY:
				return HOUR;
			case Calendar.WEEK_OF_YEAR:
				return WEEK;
			case Calendar.MILLISECOND:
				return MILLISECOND;
			case Calendar.MINUTE:
				return MINUTE;
			case Calendar.MONTH:
				return MONTH;
			case Calendar.SECOND:
				return SECOND;
			case Calendar.YEAR:
				return YEAR;
			}
			return null;
		}

		public String longLabel() {
			return ClassMessages.getString(this.getClass(), super.toString() + ".longLabel");
		}

		public String shortLabel() {
			return ClassMessages.getString(this.getClass(), super.toString() + ".shortLabel");
		}

		@Override
		public String toString() {
			return longLabel();
		}

	}

}
