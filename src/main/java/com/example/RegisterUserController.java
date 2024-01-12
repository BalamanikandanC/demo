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

import com.example.demo.pojo.RegisterUser;
import com.example.demo.repo.RegisterUserRepo;

@RestController
@CrossOrigin(origins = "*")//for all external networks we can use hitting this requests
public class RegisterUserController {
	
	@Autowired
	RegisterUserRepo repo;

	Logger log=Logger.getAnonymousLogger();
	//insert
	@PostMapping("/Newregister")
	public String register(@RequestBody RegisterUser Registeruser) {
		repo.save(Registeruser);
		return "Registration successfull...!";

	}


	//list of users
	@GetMapping("/getAllRegiseteredusers")
	public List<RegisterUser> findAllusers(){
		return repo.findAll();
	}


	//delete by id

	@DeleteMapping("/cancelRegisetereduser/{id}")
	public List<RegisterUser> cancelregistration(@PathVariable int id){
		repo.deleteById(id);
		return repo.findAll();
	}


	//search via email
	@GetMapping("/findRegisetereduserbyemail/{email}")
	public List<RegisterUser> findUser(@PathVariable String email){
		return repo.findByemail(email);
	}


//update

	//update
	@PutMapping("/updateRegisetereduser")
	public String update(@RequestBody RegisterUser Registeruser) {
	  //find the user by id
	  log.info("request hit"+Registeruser.id);
	  RegisterUser existingUser = repo.findById(Registeruser.id).orElse(null);
	  //check if the user exists
	  if (existingUser != null) {
	    //set the new values for the user fields

	    existingUser.setName(Registeruser.getName());
	    existingUser.setEmail(Registeruser.getEmail());
	    existingUser.setPassword(Registeruser.getPassword());
	    //save the updated user in the database
	    repo.save(existingUser);
	    //return a success message
	    return "Hi " + Registeruser.getName() + " is updated successfully...!";
	  } else {
	    //return an error message
	    return "User not found with id " + Registeruser.id;
	  }
	}

}
