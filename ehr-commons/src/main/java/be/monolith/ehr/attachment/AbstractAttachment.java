package be.monolith.ehr.attachment;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public abstract class AbstractAttachment {

	@Getter
	private String mimeType;

	@Getter
	private Map<String, String> metas = new HashMap<>();

	public abstract byte[] getHash();

	public abstract long getSize();

	public abstract byte[] getValue();

}
