package mapper;

import java.util.List;

import entity.Permission;

public interface PermissionMapper {
	List<Permission> getPermissions();
	Permission getPermissionByname(String permissionname);
	void addpermission(String permissionname);
	void deletepermission(long pid);
	void deleteRole_permission(long permissionid);
}
