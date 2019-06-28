package postsportal.app.service;

import java.util.List;

import postsportal.app.entity.Tag;



public interface TagServiceInterface {
	List<Tag> getByPostsId(Integer postId);
	Tag getByName(String name);
	Tag getOne(Integer tagId);
	Tag save(Tag tag);
	List<Tag> getAll();
	void remove(Integer id);
}
