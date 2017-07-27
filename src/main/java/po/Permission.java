package po;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue
    private long id;
    String permissionname;
    @OneToMany
    @JoinColumn(name = "role_permission_id")
    List<Role_permission> rp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public List<Role_permission> getRp() {
        return rp;
    }

    public void setRp(List<Role_permission> rp) {
        this.rp = rp;
    }
}
