package be.monolith.ehr.value;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * an object, device, or other 'hardware' resource
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
public class Resource {

	/**
	 * resource label
	 */
	@XmlElement(name = "label", required = true)
	@JsonProperty(required = true)
	private String label;

	/**
	 * resource role
	 */
	@NotNull
	@XmlElement(name = "role", required = true)
	@JsonProperty(required = true)
	private Code role;

	/**
	 * resource identifier, if applicable for unidentified resource coming in sets
	 * or packages, use set or package identifier, otherwise this is null
	 */
	@XmlElement(name = "identifier", required = true)
	@JsonProperty(required = true)
	private Identifier identifier;

	/**
	 * quantity, null means not applicable to resource
	 */
	@XmlElement(name = "quantity", required = false)
	@JsonProperty(required = false)
	private Quantity quantity = Quantity.ONE;
}
