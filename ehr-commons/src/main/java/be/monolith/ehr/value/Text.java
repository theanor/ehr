package be.monolith.ehr.value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

//TODO: define if this should really be an immutable object

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
public class Text {

	/**
	 * ISO 639-1 language two letter code of value language
	 */
	@NotNull
	@JsonProperty(required = true)
	@XmlAttribute(required = true)
	String language;

	/**
	 * text
	 */
	@NotNull
	@JsonProperty(required = true)
	@XmlValue
	byte[] value;

	/**
	 * should be one of text/... mime types, and should include character encoding
	 * specification
	 */
	@NotNull
	@Size(max = 128)
	@JsonProperty(required = true)
	@XmlAttribute(required = true)
	String mimeType;
}
