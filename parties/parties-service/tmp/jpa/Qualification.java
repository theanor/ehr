package be.monolith.ehr.parties.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Qualification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "terminology", column = @Column(name = "QUALIFICATION_TERM", length = Code.MAX_TERM_LENGTH)),
			@AttributeOverride(name = "value", column = @Column(name = "QUALIFICATION_VALUE", length = Code.MAX_VALUE_LENGTH)) })
	private Code code;

	@ElementCollection
	private Set<Identifier> identifiers = new HashSet<>();

	@Embedded
	private Period period;
}
