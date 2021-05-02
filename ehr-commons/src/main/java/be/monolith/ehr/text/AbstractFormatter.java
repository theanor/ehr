package be.monolith.ehr.text;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

public abstract class AbstractFormatter<T> implements Formatter<T> {

	protected Locale locale;

	public AbstractFormatter() {
		this(LocaleContextHolder.getLocale());
	}

	public AbstractFormatter(Locale locale) {
		this.locale = locale;
	}

}
