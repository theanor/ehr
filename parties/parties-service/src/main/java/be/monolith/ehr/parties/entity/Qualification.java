package be.monolith.ehr.parties.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.value.Code;
import be.monolith.ehr.value.Identifier;
import be.monolith.ehr.value.Period;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Qualification {

	private Set<Identifier> identifiers = new HashSet<>();
	private Code code;
	private Period period;

	@DBRef
	private Organization issuer;

}
