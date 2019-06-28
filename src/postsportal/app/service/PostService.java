package postsportal.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import postsportal.app.entity.Comment;
import postsportal.app.repository.CommentRepository;




@Service
public class CommentService implements CommentServiceInterface {

	@Autowired
	CommentRepository commentRepository;

	@Override
	public Comment getOne(Integer commentId) {
		return commentRepository.getOne(commentId);
	}

	@Override
	public List<Comment> getAll() {
		return commentRepository.findAll();
	}

	@Override
	public Comment save(Comment comment) {
		return commentRepository.save(comment);
	}

	@Override
	public void remove(Integer id) {
		commentRepository.delete(id);
		
	}

	@Override
	public List<Comment> getByPostId(Integer postId) {
		return commentRepository.getByPostId(postId);
	}

	@Override
	public List<Comment> getOrderByPopularity(String postId) {
		return commentRepository.getOrderByPopularity(postId);
	}

	@Override
	public List<Comment> getOrderByDate(String postId) {
		
		return commentRepository.getOrderByDate(postId);
	}
}
