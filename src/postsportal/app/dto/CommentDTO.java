package postsportal.app.controller;

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

import postsportal.app.dto.PostDTO;
import postsportal.app.dto.TagDTO;
import postsportal.app.entity.Post;
import postsportal.app.entity.Tag;
import postsportal.app.service.PostServiceInterface;
import postsportal.app.service.TagServiceInterface;



@RestController
@RequestMapping(value = "osa/tags")
public class TagController {

	@Autowired
    private TagServiceInterface tagServiceInterface;

    @Autowired
    private PostServiceInterface postServiceInterface;
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/{id}")
    public ResponseEntity<TagDTO> getUserById(@PathVariable("id") int id){
    	logger.info("GET metoda, zahtev za tag sa id:"+id );

    	Tag tag=tagServiceInterface.getOne(id);
    	if(tag == null) {
    		return new ResponseEntity<TagDTO>(HttpStatus.NOT_FOUND);
    	}
    	return new ResponseEntity<TagDTO>(new TagDTO(tag),HttpStatus.OK);
    }
    @GetMapping(value = "/all")
    public ResponseEntity<List<TagDTO>> getUsers(){
    	logger.info("GET metoda, zahtev za sve tagove" );

    	List<Tag> tag=tagServiceInterface.getAll();
    	List<TagDTO> tagDto=new ArrayList<>();
    	for(Tag t: tag) {
    		tagDto.add(new TagDTO(t));
    	}
    	return new ResponseEntity<List<TagDTO>>(tagDto, HttpStatus.OK);
    }
    
    @PostMapping(value ="/add")
    public ResponseEntity<TagDTO>addTag(@RequestParam("name") String name){
    	logger.info("POST metoda, dodavanje taga" );

        Tag tag=tagServiceInterface.getByName(name);
        if(tag != null) {
        	return new ResponseEntity<TagDTO>(new TagDTO(tag),HttpStatus.ACCEPTED);
        }
        tag = new Tag();
        tag.setName(name);
        tag=tagServiceInterface.save(tag);
        return new ResponseEntity<TagDTO>(new TagDTO(tag),HttpStatus.CREATED);
    }

    @PutMapping(value = "update/{id}",consumes = "application/json")
    public ResponseEntity<TagDTO> updateTag(@RequestBody TagDTO tagDto,@PathVariable("id") Integer id){
    	logger.info("PUT metoda, update taga sa id: "+id );

    	Tag tag=tagServiceInterface.getOne(id);
        if(tag == null) {
        	return new ResponseEntity<TagDTO>(HttpStatus.BAD_REQUEST);
        }
        tag.setName(tagDto.getName());
        tag=tagServiceInterface.save(tag);
        return new ResponseEntity<>(new TagDTO(tag),HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<Void>deleteTag(@PathVariable("id") Integer id){
    	logger.info("DELETE metoda, brisanje taga sa id:"+id );

    	Tag tag=tagServiceInterface.getOne(id);
        if(tag == null) {
        	return  new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        tagServiceInterface.remove(tag.getId());
        return  new ResponseEntity<Void>(HttpStatus.OK);
    }
    @GetMapping(value = "posts/{id}")
    public ResponseEntity<List<PostDTO>> getPostsByTag(@PathVariable("id") Integer id){
    	logger.info("GET metoda, zahtev za tagove koji pripadaju postu sa id:"+id );

    	List<Post> posts=postServiceInterface.getByTagsId(id);
        List<PostDTO>postDTOS=new ArrayList<>();
        if(posts == null) {
        	return  new ResponseEntity<List<PostDTO>>(HttpStatus.NOT_FOUND);
        }
        for(Post p:posts)
            postDTOS.add(new PostDTO(p));
        return  new ResponseEntity<List<PostDTO>>(postDTOS,HttpStatus.OK);
    }
}
