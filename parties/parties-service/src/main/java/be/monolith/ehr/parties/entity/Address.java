package be.monolith.ehr.parties.entity;

import javax.validation.constraints.NotNull;

import be.monolith.ehr.value.Period;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@NoArgsConstructor
public abstract class Address {

	@NotNull
	private Period period = Period.AT_ALL_TIMES;
}
