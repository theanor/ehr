package be.monolith.ehr.parties.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.attachment.Attachment;
import be.monolith.ehr.value.Identifier;
import be.monolith.ehr.value.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Accessors(chain = true)
public abstract class Party {

	public static final int MAX_SOURCE_LENGTH = 32;
	public static final int MAX_NAME_LENGTH = 80;

	/** internal identifier */
	private String id;

	private boolean valid;

	@NotNull
	@Size(min = 1)
	private Set<Identifier> identifiers = new HashSet<>();

	@NotNull
	private Instant date = Instant.UNKNOWN;

	@NotNull
	private String source;

	@NotNull
	private Set<PostalAddress> postalAdresses = new HashSet<>();

	@NotNull
	private Set<TelecomAddress> telecomAdresses = new HashSet<>();

	/**
	 * communication languages, in order of preference
	 */
	@NotNull
	@Size(min = 1)
	private List<String> languages = new ArrayList<>();

	private Attachment avatar;

}
