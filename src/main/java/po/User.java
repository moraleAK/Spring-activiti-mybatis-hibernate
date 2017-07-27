package po;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private long id;
	String username;
	String password;
	String tel;
	int age;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "user_user_role",
			joinColumns = @JoinColumn(name = "user", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name="user_role", referencedColumnName = "id")
	)
	List<User_role> user_roles;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<User_role> getUser_roles() {
		return user_roles;
	}

	public void setUser_roles(List<User_role> user_roles) {
		this.user_roles = user_roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", tel=" + tel + ", age=" + age + ", user_roles="
				+ user_roles + "]";
	}
	
	
}
