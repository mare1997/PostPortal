package postsportal.app.dto;

import java.io.Serializable;

import postsportal.app.entity.Tag;


public class TagDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	
	public TagDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public TagDTO() {
		super();
	}
	
	public TagDTO(Tag tag) {
		this(tag.getId(),tag.getName());
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
	
	
	
	
}
