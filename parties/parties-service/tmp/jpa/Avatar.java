package be.monolith.ehr.parties.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Version;

import be.monolith.ehr.attachment.Attachment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Entity
public class Avatar {

	private static final int MAX_MIME_TYPE_LENGTH = Attachment.MAX_MIME_TYPE_LENGTH;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Version
	@Column(nullable = false)
	private int version;

	@Basic
	@Column(length = MAX_MIME_TYPE_LENGTH, nullable = false)
	private String mimeType;

	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(nullable = false)
	private byte[] content;

}
