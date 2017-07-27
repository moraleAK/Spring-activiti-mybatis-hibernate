package mapper;

import java.util.List;

import po.Permission;
import po.Role;
import po.Role_permission;
import po.User_role;


public interface RoleMapper {
	List<Role> getRoles();
	void adduserrole(User_role ur);
	Role getRoleidbyName(String rolename);
	List<Role> getRoleinfo();
	void addRole(Role role);
	void addRolePermission(Role_permission rp);
	void deleterole(long rid);
	void deleterole_permission(long roleid);
	void deleteuser_role(long roleid);
	Role getRolebyid(long rid);
}
