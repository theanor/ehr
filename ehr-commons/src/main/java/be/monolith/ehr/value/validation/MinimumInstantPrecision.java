package be.monolith.ehr.value.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import be.monolith.ehr.value.Instant.Precision;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE,
		ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinimumInstantPrecisionValidator.class)
@Documented
public @interface MinimumInstantPrecision {

	String message() default "{minimumPrecision.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Precision value();

}
