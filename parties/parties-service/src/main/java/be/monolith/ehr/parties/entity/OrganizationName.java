package be.monolith.ehr.parties.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.value.Period;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrganizationName {

	public static final int MAX_NAME_LENGTH = 40;

	@NotNull
	@Length(max = MAX_NAME_LENGTH)
	@Indexed
	private String name;

	@NotNull
	private Period period = Period.AT_ALL_TIMES;

	@NotNull
	private String use;

}
