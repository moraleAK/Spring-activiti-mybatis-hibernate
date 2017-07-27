package mapper;

import java.util.List;

import po.User;

public interface UserMapper {
	List<User> getusers();
	User getUserByid(long id);
	void deleteuser(long uid);
	void deleteuserrole(long uid);
	void adduser(User user);
	void updateuser(User user);
	int getUidByusername(String username);
}
