package be.monolith.ehr.parties.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Practitioner covers all individuals who are engaged in the healthcare process
 * and healthcare-related services as part of their formal responsibilities.
 * Practitioners include (but are not limited to): physicians dentists
 * pharmacists physician assistants nurses scribes midwives dietitians
 * therapists optometrists paramedics medical technicians laboratory scientists
 * prosthetic technicians radiographers social workers professional homecare
 * providers official volunteers receptionists handling patient registration IT
 * personnel merging or unmerging patient records Service animal (e.g., ward
 * assigned dog capable of detecting cancer in patients, for this purpose, it is
 * considered as a person)
 * 
 * @author rba
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Practitioner extends Person {

	@Basic
	@Column(nullable = false)
	private boolean active;

	/**
	 * activity status (INAMI: HcwSituation)
	 */

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "terminology", column = @Column(name = "STATUS_TERM")),
			@AttributeOverride(name = "value", column = @Column(name = "STATUS_VALUE")) })
	private Code status;

	/**
	 * profession (INAMI: HcwProfession)
	 */
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "terminology", column = @Column(name = "PROFESSION_TERM", length = Code.MAX_TERM_LENGTH)),
			@AttributeOverride(name = "value", column = @Column(name = "PROFESSION_VALUE", length = Code.MAX_VALUE_LENGTH)) })
	private Code profession;

	/**
	 * qualifications (usually related to profession)
	 */
	@ElementCollection
	private Set<Qualification> qualifications = new HashSet<>();

}
