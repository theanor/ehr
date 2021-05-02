package be.monolith.ehr.medication;

import be.monolith.ehr.text.ClassMessages;

/**
 * Represents a time in the day when treatment is to be administered, either by
 * patient or nursing staff. The exact time in the day may vary from day to day.
 * 
 * @author robert
 *
 */
public enum AdministrationTimeInDay {

	FASTING, BEFORE_BREAKFAST, DURING_BREAKFAST, AFTER_BREAKFAST, AT_10AM, BEFORE_LUNCH, DURING_LUNCH, AFTER_LUNCH,
	AT_4PM, BEFORE_DINNER, DURING_DINNER, AFTER_DINNER, AT8PM, BEFORE_SLEEP, MORNING_ROUND, MIDAY_ROUND,
	AFTERNOON_ROUND, EVENING_ROUND, NIGHT_ROUND;

	@Override
	public String toString() {
		return ClassMessages.getString(this.getClass(), super.toString());
	}

}
