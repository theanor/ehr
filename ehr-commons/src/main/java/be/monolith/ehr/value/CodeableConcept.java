package be.monolith.ehr.value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class CodeableConcept {

	@JsonProperty(required = true)
	@XmlElement(required = true)
	@Size(min = 1)
	List<Code> codes = new ArrayList<>();

	@JsonProperty(required = true)
	@XmlElement(required = true)
	@NotNull
	Text text = null;

	public CodeableConcept(Text text, Code... codes) {
		this.codes = Arrays.asList(codes);
		this.text = text;
	}

}
