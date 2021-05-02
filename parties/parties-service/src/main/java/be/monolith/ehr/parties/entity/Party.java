package be.monolith.ehr.parties.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import be.monolith.ehr.attachment.Attachment;
import be.monolith.ehr.value.Identifier;
import be.monolith.ehr.value.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Party {

	public static final int MAX_SOURCE_LENGTH = 32;
	public static final int MAX_NAME_LENGTH = 80;

	/** internal identifier */
	@Id
	private String id;

	@NotNull
	@Size(min = 1)
	@Indexed
	private Set<Identifier> identifiers = new HashSet<>();

	@NotNull
	private Instant date = Instant.UNKNOWN;

	@NotNull
	@Length(max = MAX_SOURCE_LENGTH)
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

	@NotNull
	private Set<Relationship> relationships = new HashSet<>();

	private Attachment avatar;

	/** party this one replaces */
	@DBRef(lazy = true)
	Party replaces;

	/** party replacing this one */
	@DBRef(lazy = true)
	Party replacedBy;

}
