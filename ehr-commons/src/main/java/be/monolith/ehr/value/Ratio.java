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
public class Ratio {

	@NotNull
	@XmlElement(required = true)
	@JsonProperty(required = true)
	Quantity numerator;

	@NotNull
	@XmlElement(required = true)
	@JsonProperty(required = true)
	Quantity denominator;
}
