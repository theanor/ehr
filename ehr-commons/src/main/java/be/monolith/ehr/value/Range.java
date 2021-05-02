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
public class Range {

	/**
	 * may be null, meaning unbounded low
	 */
	@XmlElement(required = false)
	@JsonProperty(required = false)
	Quantity low;

	/**
	 * may be null, meaning unbounded high
	 */
	@XmlElement(required = false)
	@JsonProperty(required = false)
	Quantity high;

}
