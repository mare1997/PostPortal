package postsportal.app.service;

import java.util.List;

import postsportal.app.entity.Post;



public interface PostServiceInterface {
	List<Post> findAll();
	Post findOne(Integer postId);
	Post save(Post post);
	void remove(Integer id);
	List<Post> getByTagsId(Integer tagId);
	List<Post> getAllByOrderByDate();
	List<Post> getAllByOrderByPopularity();
	List<Post> getAllByCommentsCount();
	List<Post> getPostsByFilterAuthor(String param);
	List<Post> getPostsByFilterAuthorOrTag(String param1,String param2);
	
	
}
