package hub.policy.custom_exception_handler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import hub.policy.custom_exceptions.AuthenticationException;
import hub.policy.custom_exceptions.ResourceNotFoundException;
import hub.policy.dto.ApiExcResponse;



@RestControllerAdvice
public class GlobalExceptionHandler {


	// method level anno to tell SC , following is an exc handling method : to
	// handle MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		System.out.println("in method arg invalid " + e);
		List<FieldError> fieldErrors = e.getFieldErrors();// list of fiels having validation errs
		Map<String, String> map = fieldErrors.stream()
				.collect
				(Collectors.toMap
						(FieldError::getField, FieldError::getDefaultMessage));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(map);
	}

	// method level anno to tell SC , following is an exc handling method : to
	// handle : ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ApiExcResponse handleResourceNotFoundException
	(ResourceNotFoundException e) {
		System.out.println("in res not found " + e);
		return new hub.policy.dto.ApiExcResponse(e.getMessage());
	}

	@ExceptionHandler(AuthenticationException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ApiExcResponse handleAuthenticationException(AuthenticationException e) {
		System.out.println("in authentication exception " + e);
		return new hub.policy.dto.ApiExcResponse(e.getMessage());
	}

	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public hub.policy.dto.ApiExcResponse handleAccessDeniedException(AccessDeniedException e) {
		System.out.println("in access denied  exception " + e);
		return new hub.policy.dto.ApiExcResponse(e.getMessage());
	}

	// method level anno to tell SC , following is an exc handling method : to
	// handle any other remaining exc => catch all
//	@ExceptionHandler(RuntimeException.class)
//	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//	public hub.policy.dto.ApiExcResponse handleAnyException(RuntimeException e) {
//		System.out.println("in catch-all " + e);
//		return new hub.policy.dto.ApiExcResponse(e.getMessage());
//	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public hub.policy.dto.ApiExcResponse handleException(Exception e) {
		e.printStackTrace();
		System.out.println("in catch-all " + e);
		return new hub.policy.dto.ApiExcResponse(e.getMessage());
	}
	


}
