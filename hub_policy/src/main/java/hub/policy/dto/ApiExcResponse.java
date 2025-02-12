package hub.policy.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class ApiExcResponse {
	private String message;
	private LocalDateTime timeStamp;
	public ApiExcResponse(String message) {
		super();
		this.message = message;
		timeStamp=LocalDateTime.now();
	}
	
}