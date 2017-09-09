package dao;

import database.dao.GenericDaoImpl;
import entity.LeaveApply;
import org.springframework.stereotype.Repository;

/**
 * Created by Ak_Guili on 2017/8/23.
 */
@Repository
public class LeaveDao extends GenericDaoImpl<LeaveApply, Long> {

    protected Class<LeaveApply> getDomainClass() {
        return LeaveApply.class;
    }
}
