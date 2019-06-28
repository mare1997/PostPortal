package postsportal.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import postsportal.app.dto.PostDTO;
import postsportal.app.dto.TagDTO;

import postsportal.app.entity.Post;
import postsportal.app.entity.Tag;

import postsportal.app.service.PostServiceInterface;
import postsportal.app.service.TagServiceInterface;
import postsportal.app.service.UserServiceInterface;


@RestController
@RequestMapping(value="osa/posts")
public class PostController {

	@Autowired
    private PostServiceInterface postServiceInterface;

    @Autowired
    private UserServiceInterface userServiceIterface;

    @Autowired
    private TagServiceInterface tagServiceInterfce;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("id") int id){
    	logger.info("GET metoda, zahtev za post sa id:"+id );
    	Post post=postServiceInterface.findOne(id);
        if(post == null)
            return new ResponseEntity<PostDTO>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<PostDTO>(new PostDTO(post),HttpStatus.OK);
    }
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<List<PostDTO>> getPosts(){
    	logger.info("GET metoda, zahtev za sve postove");
    	List<Post> posts=postServiceInterface.findAll();
        List<PostDTO> postDto=new ArrayList<>();
        for (Post p:posts) {
            postDto.add(new PostDTO(p));
            
        }
        return new ResponseEntity<List<PostDTO>>(postDto,HttpStatus.OK);
    }
    
    @GetMapping(value = "tag/{id}")
    public ResponseEntity<List<TagDTO>> getTagByPost(@PathVariable("id") Integer id){
    	logger.info("GET metoda, zahtev za sve tagove sa post sa id: " + id );
    	List<Tag> tags=tagServiceInterfce.getByPostsId(id);
    	
        List<TagDTO>tagDto=new ArrayList<>();
        if(tags == null)
            return new ResponseEntity<List<TagDTO>>(HttpStatus.NOT_FOUND);
        else {

            for (Tag t:tags)
                tagDto.add(new TagDTO(t));
        }
        return new ResponseEntity<List<TagDTO>>(tagDto,HttpStatus.OK);
    }
   
    
    
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ResponseEntity<PostDTO> saveNewPost(@RequestParam("title") String title,
    										   @RequestParam("description") String description,
    										   @RequestParam("user_id") int user_id,
    										   @RequestParam("longitude") float longitude,
    										   @RequestParam("latitude") float latitude){
    	logger.info("POST metoda, novi post od usera: "+user_id+".");
    	Post post=new Post();
    	Date date = new Date();
    	System.out.println(title+" "+description+" "+user_id);
    	post.setTitle(title);
        post.setDescription(description);
        post.setLikes(0);
        post.setDislikes(0);
        post.setDate(date);
        post.setLatitude(latitude);
        post.setLongitude(longitude);
        post.setUser(userServiceIterface.getOne(user_id));
		post=postServiceInterface.save(post);
		
		return  new ResponseEntity<PostDTO>(new PostDTO(post),HttpStatus.CREATED);
    }
    
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable("id") int id){
    	logger.info("Put metoda, update posta sa id: "+id );
    	Post post=postServiceInterface.findOne(id);
        if(post == null) {
        	return  new ResponseEntity<PostDTO>(HttpStatus.NOT_FOUND);
        }

        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setLikes(postDTO.getLikes());
        post.setDislikes(postDTO.getDislikes());
        post.setDate(postDTO.getDate());
        post.setLongitude(postDTO.getLongitude());
        post.setLatitude(postDTO.getLatitude());
        post.setUser(userServiceIterface.getOne(postDTO.getUser().getId()));
        post=postServiceInterface.save(post);
        return new ResponseEntity<PostDTO>(new PostDTO(post),HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") int id){
    	logger.info("DELETE metoda, zahtev za brisanje posta sa id: "+id );
    	Post post= postServiceInterface.findOne(id);
        if(post == null) {
        	return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        postServiceInterface.remove(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @DeleteMapping(value="/removetags/{id}")
	public ResponseEntity<Void> deleteTags(@PathVariable("id") int id){
    	logger.info("DELETE metoda, zahtev za brisanje svih tagova sa post id:"+id );
    	Post post=postServiceInterface.findOne(id);
        if(post == null) {
        	return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        for (Tag t : post.getTags()) {
			t.getPosts().remove(post);
			tagServiceInterfce.save(t);
		}
        post.getTags().clear();
        postServiceInterface.save(post);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @PostMapping(value = "/upload_photo")
    public ResponseEntity<PostDTO> updatePost(@RequestParam("id") Integer id,@RequestParam("photo") MultipartFile photo){
    	logger.info("POST metoda, upload slike za post sa id: "+id);
    	Post post=postServiceInterface.findOne(id);
        if(post == null) {
        	return  new ResponseEntity<PostDTO>(HttpStatus.NOT_FOUND);
        }
        try {
			post.setPhoto(photo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			return  new ResponseEntity<PostDTO>(HttpStatus.BAD_REQUEST);
		}

        post=postServiceInterface.save(post);
        return new ResponseEntity<PostDTO>(new PostDTO(post),HttpStatus.OK);
    }
    @PostMapping(value="/joing_tag_and_post/{postId}/{tagId}")
	public ResponseEntity<PostDTO> setTagsInPost(@PathVariable("postId") int postId,@PathVariable("tagId") int tagId){
    	logger.info("POST metoda, zahtev za spajanje taga sa id: "+tagId +" i posta sa id: "+ postId );
    	Post post = postServiceInterface.findOne(postId);
		Tag tag = tagServiceInterfce.getOne(tagId);
		if(post != null && tag != null) {
			post.getTags().add(tag);
			tag.getPosts().add(post);
			post = postServiceInterface.save(post);
			tag = tagServiceInterfce.save(tag);
			return new ResponseEntity<PostDTO>(new PostDTO(post),HttpStatus.OK);
		}else 
			return new ResponseEntity<PostDTO>(HttpStatus.BAD_REQUEST);
	}
    //fale jos 4 funkcija, 3 za order 1 za filter
    @GetMapping(value = "/orderbydate")
    public ResponseEntity<List<PostDTO>> getPostsOrderByDate(){
    	logger.info("GET metoda, zahtev za sve postove sortiranje po datumu" );
    	List<Post> posts=postServiceInterface.getAllByOrderByDate();
        List<PostDTO> postDTOS=new ArrayList<>();
        for (Post post:posts) {
            postDTOS.add(new PostDTO(post));
        }
        return new ResponseEntity<List<PostDTO>>(postDTOS,HttpStatus.OK);
    }
    @GetMapping(value = "/orderbypopularity")
    public ResponseEntity<List<PostDTO>> getPostsOrderByPopularity(){
    	logger.info("GET metoda, zahtev za sve postove sortiranje po popularnosti" );
    	List<Post> posts=postServiceInterface.getAllByOrderByPopularity();
        List<PostDTO> postDTOS=new ArrayList<>();
        for (Post post:posts) {
            postDTOS.add(new PostDTO(post));
        }
        return new ResponseEntity<List<PostDTO>>(postDTOS,HttpStatus.OK);
    }
    @GetMapping(value = "/orderbycomments")
    public ResponseEntity<List<PostDTO>> getPostsOrderByCommentsCount(){
    	logger.info("GET metoda, zahtev za sve postove sortiranje po broju komentara" );
    	List<Post> posts=postServiceInterface.getAllByCommentsCount();
        List<PostDTO> postDTOS=new ArrayList<>();
        for (Post post:posts) {
            postDTOS.add(new PostDTO(post));
        }
        return new ResponseEntity<List<PostDTO>>(postDTOS,HttpStatus.OK);
    }
    @GetMapping(value = "/filter/{param}")
    public ResponseEntity<List<PostDTO>> getPostFilter(@PathVariable("param") String param){
    	logger.info("GET metoda, zahtev za sve postove filtrirane za vresost: "+param );
    	List<Post> posts=postServiceInterface.getPostsByFilterAuthorOrTag(param, param);
    	List<PostDTO> postDTOS=new ArrayList<>();
    	for (Post post:posts) {
            postDTOS.add(new PostDTO(post));
        }
    	
    	return new ResponseEntity<List<PostDTO>>(postDTOS,HttpStatus.OK);
    }
    
}
