package com.example;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.User;
import com.example.demo.repo.UserRepo;

@RestController
@CrossOrigin(origins = "*")//for all external networks we can use hitting this requests
public class UserController {

	@Autowired
	UserRepo repo;

	Logger log=Logger.getAnonymousLogger();
	//insert
	@PostMapping("/register")
	public String register(@RequestBody User user) {
		repo.save(user);
		return "Hi "+user.getName()+" is registered successfully...!";

	}


	//list of users
	@GetMapping("/getAllusers")
	public List<User> findAllusers(){
		return repo.findAll();
	}


	//delete by id

	@DeleteMapping("/cancel/{id}")
	public List<User> cancelregistration(@PathVariable int id){
		repo.deleteById(id);
		return repo.findAll();
	}


	//search via email
	@GetMapping("/findbyemail/{email}")
	public List<User> findUser(@PathVariable String email){
		return repo.findByemail(email);
	}


//update

	//update
	@PutMapping("/update")
	public String update(@RequestBody User user) {
	  //find the user by id
	  log.info("request hit"+user.id);
	  User existingUser = repo.findById(user.id).orElse(null);
	  //check if the user exists
	  if (existingUser != null) {
	    //set the new values for the user fields

	    existingUser.setName(user.getName());
	    existingUser.setEmail(user.getEmail());
	    existingUser.setExperience(user.getExperience());
	    existingUser.setDomain(user.getDomain());
	    //save the updated user in the database
	    repo.save(existingUser);
	    //return a success message
	    return "Hi " + user.getName() + " is updated successfully...!";
	  } else {
	    //return an error message
	    return "User not found with id " + user.id;
	  }
	}

}