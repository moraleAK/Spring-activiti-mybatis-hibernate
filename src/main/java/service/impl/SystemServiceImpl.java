package service.impl;

import java.util.List;

import mapper.PermissionMapper;
import mapper.RoleMapper;
import mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import po.Permission;
import po.Role;
import po.RolePermission;
import po.User;
import po.UserRole;
import service.SystemService;

import com.github.pagehelper.PageHelper;
@Service
@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT,timeout=5)
public class SystemServiceImpl implements SystemService{
	@Autowired
	UserMapper usermapper;
	@Autowired
	RoleMapper rolemapper;
	@Autowired
	PermissionMapper permissionmapper;


	public List<User> getallusers() {
		return usermapper.getusers();
	}

	public List<User> getpageusers(int pagenum, int pagesize) {
		PageHelper.startPage(pagenum,pagesize);  
		List<User> l=usermapper.getusers();
		return l;
	}
	public User getUserByid(long id) {
		User u=usermapper.getUserByid(id);
		return u;
	}
	public List<Role> getRoles() {
		return rolemapper.getRoles();
	}

	public void deleteuser(long uid) {
		usermapper.deleteuser(uid);
		usermapper.deleteuserrole(uid);
	}
	public void adduser(User user, String[] rolenames) {
		usermapper.adduser(user);
		for(String rolename:rolenames){
			Role role=rolemapper.getRoleidbyName(rolename);
			UserRole ur=new UserRole();
			ur.setRole(role);
			ur.setUser(user);
			rolemapper.adduserrole(ur);
		}
	}
	public void adduser(User user) {
		usermapper.adduser(user);
	}

	public void updateuser(long uid,User user, String[] rolenames) {
		if(rolenames==null){
			user.setId(uid);
			usermapper.updateuser(user);
			usermapper.deleteuserrole(uid);
		}
		else{
			user.setId(uid);
			usermapper.updateuser(user);
			usermapper.deleteuserrole(uid);
			for(String rolename:rolenames){
				Role role=rolemapper.getRoleidbyName(rolename);
				UserRole ur=new UserRole();
				ur.setRole(role);
				ur.setUser(user);
				rolemapper.adduserrole(ur);
			}
		}
		
	}
	public List<Role> getpageRoleinfo(int pagenum, int pagesize) {
		PageHelper.startPage(pagenum,pagesize);  
		List<Role> l=rolemapper.getRoleinfo();
		return l;
	}
	public List<Role> getRoleinfo() {
		return rolemapper.getRoleinfo();
	}
	public List<Permission> getPermisions() {
		return permissionmapper.getPermissions();
	}
	public void addrole(Role role, String[] permissionnames) {
		rolemapper.addRole(role);
		for(String permissionname:permissionnames){
			Permission p=permissionmapper.getPermissionByname(permissionname);
			RolePermission rp=new RolePermission();
			rp.setRole(role);
			rp.setPermission(p);
			rolemapper.addRolePermission(rp);
		}
	}
	public void deleterole(long rid) {
		rolemapper.deleterole(rid);
		rolemapper.deleterole_permission(rid);
		rolemapper.deleteuser_role(rid);
	}
	public Role getRolebyid(long rid) {
		return rolemapper.getRolebyid(rid);
	}
	public void deleterolepermission(long rid) {
		rolemapper.deleterole_permission(rid);
	}
	public void updaterole(long rid, String[] permissionnames) {
		Role role=rolemapper.getRolebyid(rid);
		for(String permissionname:permissionnames){
			Permission p=permissionmapper.getPermissionByname(permissionname);
			RolePermission rp=new RolePermission();
			rp.setRole(role);
			rp.setPermission(p);
			rolemapper.addRolePermission(rp);
		}
	}
	public List<Permission> getPagePermisions(int pagenum, int pagesize) {
		PageHelper.startPage(pagenum,pagesize);  
		return permissionmapper.getPermissions();
	}
	public void addPermission(String permissionname) {
		permissionmapper.addpermission(permissionname);
	}

	public void deletepermission(long pid) {
		permissionmapper.deletepermission(pid);
		permissionmapper.deleteRole_permission(pid);
	}
	public long getUidByusername(String username) {
		return usermapper.getUidByusername(username);
	}
	
	

}
