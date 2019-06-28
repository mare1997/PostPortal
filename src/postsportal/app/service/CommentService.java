package postsportal.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import postsportal.app.entity.Tag;



public interface TagRepository extends JpaRepository<Tag, Integer> {
	Tag getByName(String name);
	List<Tag> getByPostsId(Integer postId);
	
	
}
