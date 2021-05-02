package be.monolith.ehr.value;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
public class Period {

	public static final Period AT_ALL_TIMES = new Period(null, null);

	/**
	 * start of period, null value means unbounded
	 */
	@XmlElement(name = "start", required = false)
	@JsonProperty(required = false)
	Instant start;

	/**
	 * end of period, null value means unbounded
	 */
	@XmlElement(name = "end", required = false)
	@JsonProperty(required = false)
	Instant end;

	public boolean contains(Instant instant) {
		return ((start == null) || start.equals(instant) || start.before(instant))
				&& ((end == null) || end.equals(instant) || end.after(instant));
	}

}
