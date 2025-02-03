package hub.policy.utility;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy=DocumentNumberValidator.class)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDocumentNumber {
	
	String message() default "Invalid document number format";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default{};
	
	hub.policy.entities.DocumentType type();

}
