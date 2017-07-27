package po;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class User_role {
    @Id
    @GeneratedValue
    private long id;
    @Transient
    User user;
    @Transient
    Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
