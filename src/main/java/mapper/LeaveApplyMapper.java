package mapper;

import po.LeaveApply;

public interface LeaveApplyMapper {
	void save(LeaveApply apply);
	LeaveApply get(long id);
	void update(LeaveApply app);
}
