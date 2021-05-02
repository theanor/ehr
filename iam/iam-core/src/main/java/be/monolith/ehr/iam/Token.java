package be.monolith.ehr.iam;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {

	private String value;
	private Map<String, Object> claims;

}
