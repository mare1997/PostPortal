package postsportal.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import postsportal.app.entity.Post;
import postsportal.app.repository.PostRepository;




@Service
public class PostService implements PostServiceInterface {
	@Autowired
	PostRepository postRepository;

	@Override
	public List<Post> findAll() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}

	@Override
	public Post findOne(Integer postId) {
		// TODO Auto-generated method stub
		return postRepository.findOne(postId);
	}

	@Override
	public Post save(Post post) {
		// TODO Auto-generated method stub
		return postRepository.save(post);
	}

	@Override
	public void remove(Integer id) {
		// TODO Auto-generated method stub
		postRepository.delete(id);
	}
	@Override
    public List<Post> getByTagsId(Integer tagId) {
        return postRepository.getByTagsId(tagId);
    }

	@Override
	public List<Post> getAllByOrderByDate() {
		// TODO Auto-generated method stub
		return postRepository.getAllByOrderByDate();
	}

	@Override
	public List<Post> getAllByOrderByPopularity() {
		// TODO Auto-generated method stub
		return postRepository.getAllByOrderByPopularity();
	}

	@Override
	public List<Post> getAllByCommentsCount() {
		// TODO Auto-generated method stub
		return postRepository.getAllByCommentsCount();
	}

	@Override
	public List<Post> getPostsByFilterAuthor(String param) {
		// TODO Auto-generated method stub
		return postRepository.getPostsByFilterAuthor(param);
	}

	@Override
	public List<Post> getPostsByFilterAuthorOrTag(String param1, String param2) {
		// TODO Auto-generated method stub
		return postRepository.getPostsByFilterAuthorOrTag(param1, param2);
	}
	
	
}
