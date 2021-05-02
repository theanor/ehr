package be.monolith.ehr.text;

import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;

public class ClassMessages {

	private static ResourceBundle getBundle(Class<?> clazz) {
		return ResourceBundle.getBundle(clazz.getName(), LocaleContextHolder.getLocale());
	}

	public static String getString(Class<?> clazz, String key) {
		return getBundle(clazz).getString(key);
	}

	public static Object getObject(Class<?> clazz, String key) {
		return getBundle(clazz).getObject(key);
	}

	public static String[] getStringArray(Class<?> clazz, String key) {
		return getBundle(clazz).getStringArray(key);
	}

}
