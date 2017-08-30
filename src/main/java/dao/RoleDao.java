package dao;

import database.dao.GenericDaoImpl;
import entity.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ak_Guili on 2017/8/29.
 */
@Repository
public class RoleDao extends GenericDaoImpl<Role, Long> {
    protected Class<Role> getDomainClass() {
        return Role.class;
    }

    public List<Role> loadRoles(){
        return query("from Role").getResultList();
    }

    public Role loadRoleByName(String roleName){
        return query("from Role where role_name = ?", roleName).getSingleResult();
    }

    public List<Role> loadRolesByRoleNames(String [] roleNames){
        List<Role> roles = new ArrayList<Role>();
        for(int i= 0; i < roleNames.length; i++){
            roles.add(loadRoleByName(roleNames[i]));
        }
        return roles;
    }

    public void removeRole(long id){
        query("delete from Role where id = ?", id).executeUpdate();
    }


}
