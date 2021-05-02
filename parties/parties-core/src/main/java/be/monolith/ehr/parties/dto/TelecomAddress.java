package be.monolith.ehr.parties.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.text.ClassMessages;
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
public class TelecomAddress extends Address {

	private Use use;
	private System system;
	private String value;

	public enum Use {
		HOME, WORK, MOBILE, TEMP;

		@Override
		public String toString() {
			return ClassMessages.getString(this.getClass(), super.toString());
		}

	}

	public enum System {
		PHONE, FAX, EMAIL, PAGER, URL, SMS, OTHER;

		@Override
		public String toString() {
			return ClassMessages.getString(this.getClass(), super.toString());
		}

	}

}
