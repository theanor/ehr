package be.monolith.ehr.value;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A human actor, eg in a procedure.
 * 
 * 
 * @author robert
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
public class Actor {

	/**
	 * actor name
	 */
	@NotNull
	@XmlElement(name = "name", required = true)
	@JsonProperty(required = true)
	private String name;

	/**
	 * role code
	 */
	@NotNull
	@XmlElement(name = "role", required = true)
	@JsonProperty(required = true)
	private Code role;

	/**
	 * actor identifier
	 */
	@NotNull
	@XmlElement(name = "identifier", required = true)
	@JsonProperty(required = true)
	private Identifier identifier;
}
