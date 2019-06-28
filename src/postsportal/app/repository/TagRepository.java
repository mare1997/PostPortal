package postsportal.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import postsportal.app.entity.Comment;



public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> getByPostId(Integer postId);
	
	@Query(value="SELECT * FROM comments WHERE comments.post_id=? ORDER BY (comments.likes-comments.dislikes) DESC",nativeQuery=true)
	List<Comment> getOrderByPopularity(String param);
	
	@Query(value="SELECT * FROM comments WHERE comments.post_id=? ORDER BY comments.date DESC",nativeQuery=true)
	List<Comment> getOrderByDate(String param);
}
