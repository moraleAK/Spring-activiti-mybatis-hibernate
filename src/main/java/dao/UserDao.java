package dao;

import database.dao.GenericDaoImpl;
import org.springframework.stereotype.Repository;
import entity.User;

import java.util.List;

/**
 * Created by Ak_Guili on 2017/8/23.
 */
@Repository
public class UserDao extends GenericDaoImpl<User, Long> {
    protected Class<User> getDomainClass() {
        return User.class;
    }

    public List<User> loadUses() {
        return query("from User").getResultList();
    }

    public User loadUserByName(String userName) {
        return query("from User where userName = ?", userName).getSingleResult();
    }

    public void removeUser(long id) {
        query("delete from User where id = ?", id).executeUpdate();
    }
}