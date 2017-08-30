package mapper;

import entity.LeaveApply;

public interface LeaveApplyMapper {
	void save(LeaveApply apply);
	LeaveApply get(long id);
	void update(LeaveApply app);
}
