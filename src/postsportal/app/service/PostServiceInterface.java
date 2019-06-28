package postsportal.app.service;

import java.util.List;

import postsportal.app.entity.Comment;



public interface CommentServiceInterface {

	Comment getOne(Integer commentId);
	
	List<Comment> getAll();
	
	Comment save(Comment comment);
	
	void remove(Integer id);
	
	List<Comment> getByPostId(Integer postId);
	
	List<Comment> getOrderByPopularity(String postId);
	
	List<Comment> getOrderByDate(String postId);
	
	
	
}
