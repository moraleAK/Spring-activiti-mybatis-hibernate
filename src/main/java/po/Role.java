package po;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    private long id;
    String rolename;
    @OneToMany
    @JoinColumn(name = "user_role_id")
    List<User_role> user_roles;
    @OneToMany
    @JoinColumn(name = "role_permission_id")
    List<Role_permission> role_permissions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public List<User_role> getUser_roles() {
        return user_roles;
    }

    public void setUser_roles(List<User_role> user_roles) {
        this.user_roles = user_roles;
    }

    public List<Role_permission> getRole_permissions() {
        return role_permissions;
    }

    public void setRole_permissions(List<Role_permission> role_permissions) {
        this.role_permissions = role_permissions;
    }
}
