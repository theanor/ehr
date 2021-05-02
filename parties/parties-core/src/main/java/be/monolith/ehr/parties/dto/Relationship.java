package be.monolith.ehr.parties.dto;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.value.Code;
import be.monolith.ehr.value.Period;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Accessors(chain = true)
public class Relationship {

	@NotNull
	Code type;

	@NotNull
	Period period;

	@NotNull
	List<Code> qualifiers;

	@Min(value = 0)
	@Max(value = 100)
	byte percentage;

	@NotNull
	String fromPartyId;

	@NotNull
	String toPartyId;

}
