package mapper;

import entity.User;



public interface LoginMapper {
	User getpwdbyname(String name);
}
