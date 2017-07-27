package service;

import java.util.List;

import po.Permission;
import po.Role;
import po.User;

public interface SystemService {
    List<User> getallusers();

    List<User> getpageusers(int pagenum, int pagesize);

    User getUserByid(long id);

    List<Role> getRoles();

    void deleteuser(long uid);

    void adduser(User user, String[] rolenames);

    void adduser(User user);//只添加用户，无角色添加

    void updateuser(long uid, User user, String[] rolenames);

    List<Role> getpageRoleinfo(int pagenum, int pagesize);

    List<Role> getRoleinfo();

    List<Permission> getPermisions();

    void addrole(Role role, String[] permissionnames);

    void deleterole(long rid);

    Role getRolebyid(long rid);

    void deleterolepermission(long rid);//删除rid的角色下的所有权利

    void updaterole(long rid, String[] permissionnames);//把所有的权利permissionnames添加到rid的角色下

    List<Permission> getPagePermisions(int pagenum, int pagesize);

    void addPermission(String permissionname);

    void deletepermission(long pid);

    long getUidByusername(String username);
}
