package mapper;

import po.PurchaseApply;

public interface PurchaseApplyMapper {
	void save(PurchaseApply apply);
	PurchaseApply get(long id);
	void update(PurchaseApply apply);
}
