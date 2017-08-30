package service.impl;

import com.github.pagehelper.PageHelper;
import dao.PermissionDao;
import dao.RoleDao;
import dao.UserDao;
import entity.Permission;
import entity.Role;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.SystemService;

import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 5)
public class SystemServiceImpl implements SystemService {
    @Autowired
    UserDao userDao;
    @Autowired
    PermissionDao permissionDao;
    @Autowired
    RoleDao roleDao;


    public List<User> getUsers() {
        return userDao.loadUses();
    }

    public List<User> getpageusers(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        return userDao.loadUses();
    }

    public User getUserByid(long id) {
        return userDao.load(id);
    }

    public List<Role> getRoles() {
        return roleDao.loadRoles();
    }

    public void deleteuser(long uid) {
        userDao.removeUser(uid);
    }

    public void adduser(User user, String[] rolenames) {
        userDao.persist(user);
        user.setRoles(roleDao.loadRolesByRoleNames(rolenames));
    }

    public void adduser(User user) {
        userDao.persist(user);
    }

    public void updateuser(long uid, User user, String[] rolenames) {
        User user1 = userDao.load(uid);
        user1.setAge(user.getAge());
        user1.setUsername(user.getUsername());
        if (rolenames != null) {
            user1.setRoles(roleDao.loadRolesByRoleNames(rolenames));
        }
    }

    public List<Role> getpageRoleinfo(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        return roleDao.loadRoles();
    }

    public List<Role> getRoleinfo() {
        return roleDao.loadRoles();
    }

    public List<Permission> getPermisions() {
        return permissionDao.loadPermissions();
    }

    public void addRole(Role role, String[] permissionNames) {
        roleDao.persist(role);
        role.setPermissions(permissionDao.loadPermissionsByNames(permissionNames));
    }

    public void deleterole(long rid) {
        roleDao.removeRole(rid);
    }

    public Role getRolebyid(long rid) {
        return roleDao.load(rid);
    }

    public void deleterolepermission(long rid) {
        //rolemapper.deleterole_permission(rid);
    }

    public void updaterole(long rid, String[] permissionnames) {
        Role role = roleDao.load(rid);
        role.setPermissions(permissionDao.loadPermissionsByNames(permissionnames));
    }

    public List<Permission> getPagePermisions(int pagenum, int pagesize) {
        PageHelper.startPage(pagenum, pagesize);
        return permissionDao.loadPermissions();
    }

    public void addPermission(String permissionname) {
        Permission p = new Permission();
        p.setPermissionName(permissionname);
        permissionDao.persist(p);
    }

    public void deletepermission(long pid) {
        permissionDao.removePermission(pid);
    }

    public long getUidByusername(String username) {
        return userDao.loadUserByName(username).getId();
    }
}
