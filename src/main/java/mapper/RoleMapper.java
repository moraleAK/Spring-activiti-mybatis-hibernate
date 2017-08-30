package mapper;

import java.util.List;

import entity.Role;


public interface RoleMapper {
	List<Role> getRoles();
	//void adduserrole(UserRole ur);
	Role getRoleidbyName(String rolename);
	List<Role> getRoleinfo();
	void addRole(Role role);
	//void addRolePermission(RolePermission rp);
	void deleterole(long rid);
	void deleterole_permission(long roleid);
	void deleteuser_role(long roleid);
	Role getRolebyid(long rid);
}
