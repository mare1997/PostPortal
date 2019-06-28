package postsportal.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import postsportal.app.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	
	User getByUsername(String username);
	User getByUsernameAndPassword(String username,String password);
	
}
