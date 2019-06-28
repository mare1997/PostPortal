package postsportal.app.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import postsportal.app.dto.CommentDTO;
import postsportal.app.entity.Comment;
import postsportal.app.service.CommentServiceInterface;
import postsportal.app.service.PostServiceInterface;
import postsportal.app.service.UserServiceInterface;



@RestController
@RequestMapping(value="osa/comments")
public class CommentController {
	
	@Autowired
    private CommentServiceInterface commentServiceInterface;

    @Autowired
    private UserServiceInterface userServiceInterface;

    @Autowired
    private PostServiceInterface  postServiceInterface;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable("id") int id){
    	logger.info("GET metoda, zahtev za komentar sa id:"+id );
    	Comment comment=commentServiceInterface.getOne(id);
        if(comment == null) {
        	return new ResponseEntity<CommentDTO>(HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<CommentDTO>(new CommentDTO(comment),HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<List<CommentDTO>> getComments(){
    	logger.info("GET metoda, zahtev za sve komentare ");
    	List<Comment> comms= commentServiceInterface.getAll();
        List<CommentDTO>commentDto= new ArrayList<>();
        for (Comment c:comms) {
            commentDto.add(new CommentDTO(c));
        }
        return new ResponseEntity<List<CommentDTO>>(commentDto,HttpStatus.OK);
    }
    
    @GetMapping(value = "/post/{id}")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(@PathVariable("id")Integer id){
    	logger.info("GET metoda, zahtev za sve komentare koji pripadaju postu sa id: "+id );
    	List<Comment> comms=commentServiceInterface.getByPostId(id);
        List<CommentDTO>commentDto=new ArrayList<>();
        for (Comment c:comms) {
            commentDto.add(new CommentDTO(c));
        }

        return new ResponseEntity<List<CommentDTO>>(commentDto,HttpStatus.OK);
    }
    @PostMapping(value = "/add" )
    public ResponseEntity<CommentDTO> addComment(@RequestParam("title") String title, @RequestParam("description") String description,@RequestParam("post_id") int post_id,@RequestParam("user_id") int user_id){
    	logger.info("POST metoda, dodavanje novih komentara " );
    	Date date = new Date();
        Comment comment=new Comment();
        comment.setTitle(title);
        comment.setDescription(description);
        comment.setDate(date);
        comment.setLikes(0);
        comment.setDislikes(0);
        comment.setUser(userServiceInterface.getOne(user_id));
        comment.setPost(postServiceInterface.findOne(post_id));
        System.out.println(comment);
        comment=commentServiceInterface.save(comment);
        
    	
        return new ResponseEntity<CommentDTO>(new CommentDTO(comment),HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") int id){
    	logger.info("DELETE metoda, brisanje komentara sa id: "+id );
    	Comment comment=commentServiceInterface.getOne(id);
        if(comment == null) {
        	return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
        commentServiceInterface.remove(comment.getId());
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    
    @PutMapping(value = "/update/{id}",consumes = "application/json")
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDto, @PathVariable("id")Integer id){
    	logger.info("PUT metoda, update komentara sa id:"+id );
    	Comment comment= commentServiceInterface.getOne(id);
        if(comment == null) {
        	return  new ResponseEntity<CommentDTO>(HttpStatus.NOT_FOUND);
        }
        comment.setTitle(commentDto.getTitle());
        comment.setDescription(commentDto.getDescription());
        comment.setLikes(commentDto.getLikes());
        comment.setDislikes(commentDto.getDislikes());

        comment=commentServiceInterface.save(comment);
        return new ResponseEntity<CommentDTO>(new CommentDTO(comment),HttpStatus.OK);

    }
    
    
    
    //fale 2 funkicije za sortiranje
    @GetMapping(value = "/orderByPopularity/{id}")
    public ResponseEntity<List<CommentDTO>>getCommentsOrderPop(@PathVariable("id")String postId){
    	logger.info("GET metoda, zahtev za sve komentare sortirane po popularnosti za post sa id:" +postId);
    	List<Comment> comments=commentServiceInterface.getOrderByPopularity(postId);
        List<CommentDTO>commentDTOS=new ArrayList<>();
        for (Comment comment:comments) {
            commentDTOS.add(new CommentDTO(comment));
        }

        return new ResponseEntity<List<CommentDTO>>(commentDTOS,HttpStatus.OK);
    }
    
    @GetMapping(value = "/orderByDate/{id}")
    public ResponseEntity<List<CommentDTO>>getCommentsOrderDate(@PathVariable("id")String postId){
    	logger.info("GET metoda, zahtev za sve komentare sortirane po datumu za post sa id:" +postId);
    	List<Comment> comments=commentServiceInterface.getOrderByDate(postId);
        List<CommentDTO>commentDTOS=new ArrayList<>();
        for (Comment comment:comments) {
            commentDTOS.add(new CommentDTO(comment));
        }

        return new ResponseEntity<List<CommentDTO>>(commentDTOS,HttpStatus.OK);
    }
}
