package be.monolith.ehr.value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
public class Code {

	public static final int MAX_TERM_LENGTH = 32;
	public static final int MAX_VALUE_LENGTH = 255;
	private static final int MAX_VERSION_LENGTH = 16;

	/**
	 * terminology identifier should be known of terminology service, where
	 * meta-data (e.g version, issuer, ...) and search services are available
	 */
	@Size(max = MAX_TERM_LENGTH)
	@NotNull
	@JsonProperty(required = true)
	@XmlElement(required = true)
	private String system;

	/**
	 * code value, language agnostic concept identifier, may contain a label using a
	 * terminology specific syntax (eg snomed-ct "<concept-id> | <concept
	 * description> |")
	 */
	@Size(max = MAX_VALUE_LENGTH)
	@NotNull
	@JsonProperty(required = true)
	@XmlElement(required = true, nillable = false)
	private String value;

	@Size(max = MAX_VERSION_LENGTH)
	@JsonProperty(required = false)
	@XmlElement(required = false, nillable = false)
	private String version;

	public Code(@Size(max = MAX_TERM_LENGTH) @NotNull String system,
			@Size(max = MAX_VALUE_LENGTH) @NotNull String value) {
		super();
		this.system = system;
		this.value = value;
	}

}
