package be.monolith.ehr.value.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.monolith.ehr.value.Instant;
import be.monolith.ehr.value.Instant.Precision;

public class MinimumInstantPrecisionValidator implements ConstraintValidator<MinimumInstantPrecision, Instant> {

	private Precision minimumPrecision;

	@Override
	public void initialize(MinimumInstantPrecision constraintAnnotation) {
		this.minimumPrecision = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(Instant value, ConstraintValidatorContext context) {
		return (value == null) || (value.getPrecision().ordinal() >= minimumPrecision.ordinal());
	}

}
