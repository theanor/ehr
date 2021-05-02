package be.monolith.ehr.attachment;

import java.net.URI;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
@XmlRootElement(name = "attachment")
public class Attachment {

	public static final int MAX_MIME_TYPE_LENGTH = 128;

	@NotNull
	@Size(max = MAX_MIME_TYPE_LENGTH)
	@XmlAttribute(required = true)
	@JsonProperty(required = true)
	private String mimeType;

	@XmlElement(required = false)
	@JsonProperty(required = false)
	private byte[] hash;

	@XmlElement(required = false)
	@JsonProperty(required = false)
	private byte[] value;

	@XmlElement(required = false)
	@JsonProperty(required = false)
	private URI uri;

	@XmlAttribute(required = false)
	@JsonProperty(required = false)
	private Long size;

//	@XmlElement(required = false)
//	@JsonProperty(required = false)
//	private Instant date;
//
//	@XmlElement(required = false)
//	@JsonProperty(required = false)
//	private String title;

	// TODO document this class
	// TODO add metadata

	@Override
	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof Attachment)) {
			Attachment other = (Attachment) obj;
			return other.getHash().equals(this.getHash());
		} else
			return false;
	}

}
