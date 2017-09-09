package mapper;

import entity.User;

import java.util.List;

public interface UserMapper {
	List<User> getusers();
	User getUserByid(long id);
	void deleteuser(long id);
	void deleteuserrole(long id);
	void adduser(User user);
	void updateuser(User user);
	long getUidByusername(String username);
}
