package be.monolith.ehr.value;

import javax.validation.constraints.NotNull;
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
public class Quantity {

	public static final Quantity ONE = new Quantity(1, null);

	/**
	 * any number value
	 */
	@NotNull
	@XmlElement(required = true, nillable = false)
	@JsonProperty(required = true)
	Number value;

	/**
	 * may be null, meaning this is an absolute quantity (otherwise, it's a measure)
	 */
	@XmlElement(required = false)
	@JsonProperty(required = false)
	Code unit;

}
