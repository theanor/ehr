package be.monolith.ehr.parties.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.value.Code;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Accessors(chain = true)
public class Practitioner extends Person {

	@NotNull
	private Code status;

	@NotNull
	private Code profession;

	@NotNull
	private List<Qualification> qualifications = new ArrayList<>();

}
