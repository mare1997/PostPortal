package postsportal.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import postsportal.app.entity.Tag;
import postsportal.app.repository.TagRepository;



@Service
public class TagService implements TagServiceInterface {

	@Autowired
	TagRepository tagRepository;

	@Override
	public List<Tag> getByPostsId(Integer postId) {
		// TODO Auto-generated method stub
		return tagRepository.getByPostsId(postId);
	}

	@Override
	public Tag getByName(String name) {
		// TODO Auto-generated method stub
		return tagRepository.getByName(name);
	}

	@Override
	public Tag getOne(Integer tagId) {
		
		return tagRepository.findOne(tagId);
	}

	@Override
	public Tag save(Tag tag) {
		return tagRepository.save(tag);
	}

	@Override
	public List<Tag> getAll() {
		
		return tagRepository.findAll();
	}

	@Override
	public void remove(Integer id) {
		tagRepository.delete(id);
		
	}
	
	
}
