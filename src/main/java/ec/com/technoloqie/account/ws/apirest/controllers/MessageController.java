package ec.com.technoloqie.account.ws.apirest.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://127.0.0.1:4200"})
@RestController
public class MessageController {
	
	@GetMapping("/messages")
    public String getMessage() {
        return "Hello from tec-account-ws!";
    }
	
}
