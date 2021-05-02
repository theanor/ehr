package be.monolith.ehr.parties.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class Organization extends Party {

	@NotNull
	Set<OrganizationName> names = new HashSet<>();

	@NotNull
	@Size(min = 1)
	private Set<Code> statuses = new HashSet<>();

	@NotNull
	@Size(min = 1)
	private Set<Code> types = new HashSet<>();

}
