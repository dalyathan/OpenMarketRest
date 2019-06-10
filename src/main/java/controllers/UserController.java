package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.HashMap;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import models.User;
import repositories.UserRepository;
import services.UserRepositoryService;


@RestController
@RequestMapping(path="/users",produces="application/json")
@CrossOrigin(origins="*")
public class UserController{
	private UserRepositoryService userRepositoryService;
	
	
	@Autowired
	public UserController(UserRepositoryService userRepositoryService) {
		this.userRepositoryService=userRepositoryService;
	}
	
	
	@GetMapping("/{username}/profile_image")//not tested
	public ResponseEntity<String> getProfileForUsername(@PathVariable String username) {
		String file_name=this.userRepositoryService.findByUsername(username).getPictureId();
		return getImageJsonFromfile(file_name);
	}
	
	
	private ResponseEntity<String> getImageJsonFromfile(String fileName){
		String absolutePath="C:\\Users\\Windows\\Documents\\STSProjects\\MarketRest\\src\\main\\resources\\static\\images\\"+fileName;
		byte[] fileContent;
		try {
			fileContent = FileUtils.readFileToByteArray(new File(absolutePath));
		} catch (IOException e) {
			// for debugging purposes
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(Base64.getEncoder().encodeToString(fileContent),HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<retrofitclassesmapper.User> findById(@PathVariable("id") long id) {
		Optional<User> optionalUser=this.userRepositoryService.findById(id);
		if(optionalUser.isPresent()) {
			return new ResponseEntity<retrofitclassesmapper.User>(new retrofitclassesmapper.User(optionalUser.get()),HttpStatus.OK);
		}
		return new ResponseEntity<retrofitclassesmapper.User>(HttpStatus.NOT_FOUND);
	}
	
	
	@PutMapping("/")
	public ResponseEntity saveImageIn(@PathVariable("username") String fileName,@RequestBody String encodedImage) {
		String absolutePath="C:\\Users\\Windows\\Documents\\STSProjects\\MarketRest\\src\\main\\resources\\static\\images\\"+fileName;
		byte[] decodedBytes = Base64.getDecoder().decode(encodedImage);
		try {
			FileUtils.writeByteArrayToFile(new File(absolutePath), decodedBytes);
		} catch (IOException e) {
			// for debugging purposes
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	@GetMapping("/username/{username}")
	public ResponseEntity<retrofitclassesmapper.User> findByUsername(@PathVariable("username") String username) {
		User user=this.userRepositoryService.findByUsername(username);
		if(user!=null) {
			return new ResponseEntity<>(new retrofitclassesmapper.User(user),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping
	public ResponseEntity register(@Autowired PasswordEncoder bcyrptPasswordEncoder,@RequestBody retrofitclassesmapper.User user) {
		User newUser=new User(user);
		newUser.setPassword(bcyrptPasswordEncoder.encode(newUser.getPassword()));
		this.userRepositoryService.save(newUser);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity update(@RequestBody retrofitclassesmapper.User user) {
		Optional<User> optionalUser=this.userRepositoryService.findById(user.getId());
		if(optionalUser.isPresent()) {
			User fromDb=optionalUser.get();
			fromDb.updateUser(user);
			this.userRepositoryService.save(fromDb);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable("id") long id) {
		if(this.userRepositoryService.existsById(id)) {
			this.userRepositoryService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/image/{location_id}")
	public ResponseEntity<String> getProfileImage(@PathVariable("location_id") String locationId) {
		return getImageJsonFromfile(locationId);
	}
}
