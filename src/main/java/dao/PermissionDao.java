package dao;

import database.dao.GenericDaoImpl;
import entity.Permission;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ak_Guili on 2017/8/29.
 */
@Repository
public class PermissionDao extends GenericDaoImpl<Permission, Long>{
    protected Class<Permission> getDomainClass() {
        return Permission.class;
    }

    public List<Permission> loadPermissions(){
        return query("from Permission").getResultList();
    }

    public List<Permission> loadPermissionsByNames(String[] permissionNames){
        List<Permission> permissions = new ArrayList<Permission>();
        for(int i = 0; i< permissionNames.length; i++){
            permissions.add(loadPermissionByName(permissionNames[i]));
        }
        return permissions;
    }

    public Permission loadPermissionByName(String permissionName){
        return query("from Permission where permission_name = ?",permissionName).getSingleResult();
    }

    public void removePermission(long id){
        query("delete from Permission where id =?", id).executeUpdate();
    }
}
