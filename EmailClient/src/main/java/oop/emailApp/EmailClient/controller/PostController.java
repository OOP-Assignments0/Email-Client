package oop.emailApp.EmailClient.controller;

import org.springframework.web.bind.annotation.*;



@CrossOrigin
@RestController
public class PostController {
	//IteratorFactory IF = new IteratorFactory();
	 @PostMapping("/signUP")
	    public void SignUp(@RequestBody String jsonString){
		 
	    }
}
