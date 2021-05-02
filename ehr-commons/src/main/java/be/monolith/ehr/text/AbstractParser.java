package be.monolith.ehr.text;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

public abstract class AbstractParser<T> implements Parser<T> {

	protected Locale locale;

	public AbstractParser() {
		this(LocaleContextHolder.getLocale());
	}

	public AbstractParser(Locale locale) {
		this.locale = locale;
	}

}
