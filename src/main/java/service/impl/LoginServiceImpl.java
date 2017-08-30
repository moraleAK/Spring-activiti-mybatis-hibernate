package service.impl;


import dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import entity.User;
import service.LoginService;
@Transactional
@Service
public class LoginServiceImpl implements LoginService{
	/*@Autowired
	LoginMapper loginmapper;*/

	@Autowired
	UserDao userDao;
	public String getpwdbyname(String name) {
		User s=userDao.loadUserByName(name);
		if(s!=null)
		return s.getPassword();
		else
		return null;
	}

}
