package service;

import entity.PurchaseApply;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.Map;

public interface PurchaseService {
	public ProcessInstance startWorkflow(PurchaseApply apply,String userid,Map<String,Object> variables);
	PurchaseApply getPurchase(int id);
	void updatePurchase(PurchaseApply a);
}
