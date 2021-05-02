package be.monolith.ehr.parties.jpa;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Party {

	private static final int MAX_SOURCE_LENGTH = be.monolith.ehr.parties.dto.Party.MAX_SOURCE_LENGTH;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;

	@Version
	@Column(nullable = false)
	private int version;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date date;

	@Basic
	@Column(length = MAX_SOURCE_LENGTH, name = "source", nullable = false)
	private String source;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(indexes = { @Index(columnList = "issuer,scheme,value") })
	private Set<Identifier> identifiers = new HashSet<>();

	@ElementCollection(fetch = FetchType.LAZY)
	private Set<PostalAddress> postalAddresses = new HashSet<>();

	@ElementCollection(fetch = FetchType.LAZY)
	private Set<TelecomAddress> telecomAddresses = new HashSet<>();

	@ElementCollection(fetch = FetchType.LAZY)
	private Set<Relationship> relationships = new HashSet<>();

	/* comma separated list of iso 639 codes, in order of preference */
	@Basic
	@Column(length = 21)
	private String languages;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Avatar avatar;

}
