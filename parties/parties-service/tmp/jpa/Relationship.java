package be.monolith.ehr.parties.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Embeddable
public class Relationship {
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "terminology", column = @Column(name = "RELATIONSHIP_TERM", length = Code.MAX_TERM_LENGTH)),
			@AttributeOverride(name = "value", column = @Column(name = "RELATIONSHIP_VALUE", length = Code.MAX_VALUE_LENGTH)) })
	Code relationship;

	@Embedded
	Period period;

	/** INAMI : hcwRole, for healthcare workers */
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "terminology", column = @Column(name = "ROLE_TERM", length = Code.MAX_TERM_LENGTH)),
			@AttributeOverride(name = "value", column = @Column(name = "ROLE_VALUE", length = Code.MAX_VALUE_LENGTH)) })
	Code role;

	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "related_party_id")
	Party party;
}
