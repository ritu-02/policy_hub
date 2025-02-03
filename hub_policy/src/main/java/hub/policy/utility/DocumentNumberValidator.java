package hub.policy.utility;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DocumentNumberValidator implements ConstraintValidator<ValidDocumentNumber,String> {
	
	private hub.policy.entities.DocumentType documentType;
	
	@Override
	public void initialize(ValidDocumentNumber constraintAnnotation) {
		this.documentType=constraintAnnotation.type();
	}

	@Override
	public boolean isValid(String documentNumber, ConstraintValidatorContext context) {
		if(documentNumber == null)
		    return false;
		
		String regex;
		switch(documentType) {
			case AADHARCARD:
				regex="^[2-9]{1}[0-9]{11}$"; // aadhar - 12 digit
				break;
			case VOTERID:
				regex="^[A-Z]{3}[0-9]{7}$";
				break;
			case PANCARD:
				regex="^[A-Z]{5}[0-9]{4}[A-Z]$";
				break;
			case PASSPORT:
				regex="^[A-Z][0-9]{7}$";
				break;
			case DRIVINGLICENCE:
				regex="^[A-Z]{2}[0-9]{2}\\s[0-9]{4,11}$";
				break;
			default:
				return false;
			
				
		}
		
		return regex !=null && Pattern.matches(regex, documentNumber);
	}

}
