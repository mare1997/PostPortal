package postsportal.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import postsportal.app.entity.Post;



public interface PostRepository extends JpaRepository<Post, Integer> {

	List<Post> getByTagsId(Integer tagId);
	
	@Query(value="SELECT * FROM posts ORDER BY posts.date DESC",nativeQuery=true)
	List<Post> getAllByOrderByDate();
	
	@Query(value="SELECT * FROM posts ORDER BY (posts.likes-posts.dislikes) DESC",nativeQuery=true)
	List<Post> getAllByOrderByPopularity();
	
	@Query(value="SELECT posts.* FROM posts  LEFT OUTER JOIN comments  ON posts.post_id = comments.post_id GROUP BY posts.post_id ORDER BY COUNT(DISTINCT comments.comment_id) DESC",nativeQuery=true)
	List<Post> getAllByCommentsCount();
	
	@Query(value="SELECT posts.* FROM posts LEFT OUTER JOIN users ON posts.user_id = users.user_id WHERE users.username LIKE ? GROUP BY posts.post_id ",nativeQuery=true)
	List<Post> getPostsByFilterAuthor(String param);
	
	@Query(value="SELECT posts.* FROM posts LEFT OUTER JOIN post_tags ON posts.post_id=post_tags.post_id LEFT OUTER JOIN tags ON tags.tag_id = post_tags.tag_id WHERE tags.name LIKE 'NEW' GROUP BY posts.post_id ",nativeQuery=true)
	List<Post> getPostsByFilterTag(String param);
	
	@Query(value="SELECT posts.* FROM posts INNER JOIN users ON posts.user_id = users.user_id LEFT OUTER JOIN post_tags ON posts.post_id=post_tags.post_id LEFT OUTER JOIN tags ON tags.tag_id = post_tags.tag_id WHERE tags.name LIKE ? OR users.username LIKE ?  GROUP BY posts.post_id",nativeQuery = true)
	List<Post> getPostsByFilterAuthorOrTag(String param1,String param2);
}
