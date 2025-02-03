package hub.policy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hub.policy.dto.UserResponseDto;
import hub.policy.entities.User;
import hub.policy.service.UserAdminService;

@RestController
@RequestMapping("/useradmin")
public class UserAdminController {
     @Autowired
	private UserAdminService userAdminService;
     
     @GetMapping
     public ResponseEntity<?> getAllUserDetails(){
    	 List<UserResponseDto> usersDetails=userAdminService.getAllUsersList();
    	 if(usersDetails.isEmpty())
    		 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	 return ResponseEntity.ok(usersDetails);
     }
}
