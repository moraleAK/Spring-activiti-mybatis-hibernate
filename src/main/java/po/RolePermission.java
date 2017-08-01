package po;

import javax.persistence.*;

@Entity
@Table(name = "role_permission")
public class RolePermission {
    @Id
    @GeneratedValue
    private long id;
    @Transient
    Role role;
    @Transient
    Permission permission;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
