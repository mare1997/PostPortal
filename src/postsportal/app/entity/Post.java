package postsportal.app.dto;



import java.io.Serializable;

import postsportal.app.entity.User;
import postsportal.app.entity.User.UserType;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
    private String name;
    private String username;
    private String password;
    private UserType userType;
    private byte[] photo;
    
    
    
	public UserDTO() {
		super();
	}

	public UserDTO(Integer id, String name, String username, String password, UserType userType, byte[] photo) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.photo = photo;
	}
	public UserDTO(User user) {
        this(user.getId(),user.getName(),user.getUsername(),user.getPassword(),user.getUserType(),user.getPhoto());
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

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
    
}
