package be.monolith.ehr.parties.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.text.ClassMessages;
import be.monolith.ehr.value.Period;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Accessors(chain = true)
public class OrganizationName {

	public static final int MAX_NAME_LENGTH = 40;

	@NotNull
	private String name;

	@NotNull
	private Period period = Period.AT_ALL_TIMES;

	@NotNull
	private Use use = Use.OFFICIAL;

	public enum Use {

		OFFICIAL, ALIAS;

		@Override
		public String toString() {
			return ClassMessages.getString(this.getClass(), super.toString());
		}

	}

}
