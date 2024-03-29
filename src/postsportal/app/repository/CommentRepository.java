package postsportal.app.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity                 
@Table(name="tags")
public class Tag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id                                 
	@GeneratedValue(strategy=IDENTITY)  
	@Column(name="tag_id", unique=true, nullable=false) 
	private Integer id;
	
	@Column(name="name", unique=false, nullable=false, length = 30)
	private String name;
	
	@ManyToMany(cascade = { CascadeType.PERSIST,CascadeType.MERGE }, fetch = LAZY,mappedBy = "tags")
	private Set<Post> posts = new HashSet<Post>();

	
	
	public Tag() {
		super();
	}

	public Tag(Integer id, String name, Set<Post> posts) {
		super();
		this.id = id;
		this.name = name;
		this.posts = posts;
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

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
	
	
}
