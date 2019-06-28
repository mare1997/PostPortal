package postsportal.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import postsportal.app.dto.UserDTO;
import postsportal.app.entity.User;
import postsportal.app.service.UserServiceInterface;



@RestController
@RequestMapping(value = "osa/users")
public class UserControler {

	@Autowired
	private UserServiceInterface userServiceIterface;
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id){
    	logger.info("GET metoda, zahtev za user sa id:"+id );

		User user= userServiceIterface.getOne(id);
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers(){
    	logger.info("GET metoda, zahtev za sve usere" );

		List<User> users = userServiceIterface.getAll();
		List<UserDTO> userDTO = new ArrayList<UserDTO>();
		for (User u : users) {
			userDTO.add(new UserDTO(u));
		}
		return new ResponseEntity<List<UserDTO>>(userDTO, HttpStatus.OK);
	}
	
	@GetMapping(value = "get/{username}")
	public ResponseEntity<UserDTO> getUserbyUsername(@PathVariable("username") String username){
    	logger.info("GET metoda, zahtev za user sa username: "+ username );

		User user= userServiceIterface.getByUsername(username);
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.OK);
	}
	@GetMapping(value = "get/{username}/{password}")
	public ResponseEntity<UserDTO> getUserbyUsername(@PathVariable("username") String username,@PathVariable("password") String password){
		logger.info("GET metoda, zahtev za user sa username: "+ username + " i password:" +password);
		User user= userServiceIterface.getByUsernameAndPassword(username, password);
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.OK);
	}
	@PostMapping(value = "/add")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDto){
		logger.info("POST metoda, dodavanje usera" );
		User username=userServiceIterface.getByUsername(userDto.getUsername());
		if(username != null) {
			return new ResponseEntity<UserDTO>(HttpStatus.FORBIDDEN);
		}
		User user= new User();
		user.setName(userDto.getName());
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setUserType(userDto.getUserType());
		userServiceIterface.save(user);
		return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/update/{id}" ,consumes = "application/json" )
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDto,@PathVariable("id")int id){
		logger.info("PUT metoda, update usera sa id: "+id );
		User user=userServiceIterface.getOne(id);
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setUserType(userDto.getUserType());
		userServiceIterface.save(user);
		return new ResponseEntity<UserDTO>(new UserDTO(user),HttpStatus.OK);
		
	}
	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") int id){
		logger.info("Delete metoda, zahtev za brisanje usera sa id: "+ id );
		User user= userServiceIterface.getOne(id);
		if(user == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		userServiceIterface.remove(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/upload_photo")
    public ResponseEntity<Void> uploadUserPhoto(@RequestParam("id") Integer id,@RequestParam("photo") MultipartFile photo){
		logger.info("POST metoda, upload slike za user sa id: "+id);
		User user=userServiceIterface.getOne(id);
        if(user == null) {
        	return  new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        
    	
        try {
        	user.setPhoto(photo.getBytes());
        	
        	
		} catch (IOException e) {
			e.printStackTrace();
			return  new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

        user=userServiceIterface.save(user);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
