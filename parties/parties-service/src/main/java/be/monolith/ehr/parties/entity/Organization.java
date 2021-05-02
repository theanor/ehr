package be.monolith.ehr.parties.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.value.Code;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class Organization extends Party {

	@NotNull
	@Length(min = 1)
	@Indexed
	Set<OrganizationName> names = new HashSet<>();

	@NotNull
	@Size(min = 1)
	private Set<Code> statuses = new HashSet<>();

	@NotNull
	@Size(min = 1)
	private Set<Code> types = new HashSet<>();

	@NotNull
	private List<Qualification> qualifications = new ArrayList<>();

}
