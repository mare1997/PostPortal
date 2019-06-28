package postsportal.app.entity;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity                 
@Table(name="users") 
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public enum UserType {ADMIN,USER,PUBLISHER}
	
	@Id                                 
	@GeneratedValue(strategy=IDENTITY) 
	@Column(name="user_id", unique=true, nullable=false) 
	private Integer id;
	
	@Column(name="name", unique=false, nullable=false, length = 30)
	private String name;
	
	@Column(name="username", unique=false, nullable=false, length = 30)
	private String username;
	  
	@Column(name="pasword", unique=false, nullable=false, length = 10)
	private String password;
	
	@Lob
	@Basic(fetch = LAZY)
	@Column(name = "photo", unique = false, nullable = true)
	private byte[] photo;
	
	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "user")
	private Set<Post> posts=new HashSet<Post>();

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "user")
	private Set<Comment> comments=new HashSet<Comment>();
	
	@Enumerated(EnumType.STRING)
	@Column(name="user_type", unique=false, nullable=false)
	private UserType userType;
	
	public User() {
		super();
	}


	public User(Integer id, String name, String username, String password, byte[] photo,
			Set<postsportal.app.entity.Post> posts, Set<postsportal.app.entity.Comment> comments) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.posts = posts;
		this.comments = comments;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}


	public Set<Post> getPosts() {
		return posts;
	}


	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}


	public Set<Comment> getComments() {
		return comments;
	}


	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}


	public UserType getUserType() {
		return userType;
	}


	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	
	
	
	
}
