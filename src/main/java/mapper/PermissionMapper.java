package mapper;

import entity.Permission;

import java.util.List;

public interface PermissionMapper {
	List<Permission> getPermissions();
	Permission getPermissionByname(String permissionname);
	void addpermission(String permissionname);
	void deletepermission(long pid);
	void deleteRole_permission(long permissionid);
}
