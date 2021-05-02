package be.monolith.ehr.parties.jpa;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Accessors(chain = true)
public class Organization extends Party {

	/** activity status (INAMI: HciSituation) */
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "terminology", column = @Column(name = "STATUS_TERM", length = Code.MAX_TERM_LENGTH)),
			@AttributeOverride(name = "value", column = @Column(name = "STATUS_VALUE", length = Code.MAX_VALUE_LENGTH)) })
	private Code status;

	/** type of organization (INAMI : HciType) */
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "terminology", column = @Column(name = "TYPE_TERM", length = Code.MAX_TERM_LENGTH)),
			@AttributeOverride(name = "value", column = @Column(name = "TYPE_VALUE", length = Code.MAX_VALUE_LENGTH)) })
	private Code type;

	/** names */
	@ElementCollection(fetch = FetchType.EAGER)
	private List<OrganizationName> names;

}
