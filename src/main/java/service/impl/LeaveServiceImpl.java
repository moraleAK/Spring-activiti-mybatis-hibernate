package service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.LeaveDao;
import dao.UserDao;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.LeaveApply;
import service.LeaveService;

@Transactional
@Service
public class LeaveServiceImpl implements LeaveService {
 /*   @Autowired
    LeaveApplyMapper leavemapper;*/
    @Autowired
    IdentityService identityservice;
    @Autowired
    RuntimeService runtimeservice;
    @Autowired
    TaskService taskservice;
    @Autowired
    UserDao userDao;
    @Autowired
    LeaveDao leaveDao;

    public ProcessInstance startWorkflow(LeaveApply apply, String userid, Map<String, Object> variables) {
        apply.setApplyTime(new Date().toString());
        apply.setUserId(userid);
        leaveDao.persist(apply);
        String businesskey = String.valueOf(apply.getId());//使用leaveapply表的主键作为businesskey,连接业务数据和流程数据
        identityservice.setAuthenticatedUserId(userid);
        ProcessInstance instance = runtimeservice.startProcessInstanceByKey("leave", businesskey, variables);
        System.out.println(businesskey);
        String instanceid = instance.getId();
        apply.setProcessInstanceId(instanceid);
        return instance;
    }

    public List<LeaveApply> getpagedepttask(String userid, int firstrow, int rowcount) {
        List<LeaveApply> results = new ArrayList<LeaveApply>();
        List<Task> tasks = taskservice.createTaskQuery().taskCandidateGroup("departmentManager").listPage(firstrow, rowcount);
        tasks = taskservice.createTaskQuery().taskAssignee("admin").list();
        for (Task task : tasks) {
            String instanceid = task.getProcessInstanceId();
            ProcessInstance ins = runtimeservice.createProcessInstanceQuery().processInstanceId(instanceid).singleResult();
            String businesskey = ins.getBusinessKey();
            LeaveApply a = leaveDao.load(Long.valueOf(businesskey));
            a.setTask(task);
            results.add(a);
        }
        return results;
    }

    public int getalldepttask(String userid) {
        List<Task> tasks = taskservice.createTaskQuery().taskCandidateGroup("departmentManager").list();
        return tasks.size();
    }

    public LeaveApply loadLeave(long id) {
        return leaveDao.load(id);
    }

    public List<LeaveApply> getpagehrtask(String userid, int firstrow, int rowcount) {
        List<LeaveApply> results = new ArrayList<LeaveApply>();
        List<Task> tasks = taskservice.createTaskQuery().taskCandidateGroup("HR").listPage(firstrow, rowcount);
        for (Task task : tasks) {
            String instanceid = task.getProcessInstanceId();
            ProcessInstance ins = runtimeservice.createProcessInstanceQuery().processInstanceId(instanceid).singleResult();
            String businesskey = ins.getBusinessKey();
            LeaveApply a = leaveDao.load(Long.valueOf(businesskey));
            a.setTask(task);
            results.add(a);
        }
        return results;
    }

    public int getallhrtask(String userid) {
        List<Task> tasks = taskservice.createTaskQuery().taskCandidateGroup("HR").list();
        return tasks.size();
    }

    public List<LeaveApply> getpageXJtask(String userid, int firstrow, int rowcount) {
        List<LeaveApply> results = new ArrayList<LeaveApply>();
        List<Task> tasks = taskservice.createTaskQuery().taskCandidateOrAssigned(userid).taskName("cancelLeave").listPage(firstrow, rowcount);
        for (Task task : tasks) {
            String instanceid = task.getProcessInstanceId();
            ProcessInstance ins = runtimeservice.createProcessInstanceQuery().processInstanceId(instanceid).singleResult();
            String businesskey = ins.getBusinessKey();
            LeaveApply a = leaveDao.load(Long.valueOf(businesskey));
            a.setTask(task);
            results.add(a);
        }
        return results;
    }

    public int getallXJtask(String userid) {
        List<Task> tasks = taskservice.createTaskQuery().taskCandidateOrAssigned(userid).taskName("cancelLeave").list();
        return tasks.size();
    }

    public List<LeaveApply> getpageupdateapplytask(String userid, int firstrow, int rowcount) {
        List<LeaveApply> results = new ArrayList<LeaveApply>();
        List<Task> tasks = taskservice.createTaskQuery().taskCandidateOrAssigned(userid).taskName("adjugeApply").listPage(firstrow, rowcount);
        for (Task task : tasks) {
            String instanceid = task.getProcessInstanceId();
            ProcessInstance ins = runtimeservice.createProcessInstanceQuery().processInstanceId(instanceid).singleResult();
            String businesskey = ins.getBusinessKey();
            LeaveApply a = leaveDao.load(Long.valueOf(businesskey));
            a.setTask(task);
            results.add(a);
        }
        return results;
    }

    public int getallupdateapplytask(String userid) {
        List<Task> tasks = taskservice.createTaskQuery().taskCandidateOrAssigned(userid).taskName("adjugeApply").list();
        return tasks.size();
    }

    public void completereportback(String taskid, String realstart_time, String realend_time) {
        Task task = taskservice.createTaskQuery().taskId(taskid).singleResult();
        String instanceid = task.getProcessInstanceId();
        ProcessInstance ins = runtimeservice.createProcessInstanceQuery().processInstanceId(instanceid).singleResult();
        String businesskey = ins.getBusinessKey();
        LeaveApply a = leaveDao.load(Long.valueOf(businesskey));
        a.setRealityStartTime(realstart_time);
        a.setRealityEndTime(realend_time);
        taskservice.complete(taskid);
    }

    public void updatecomplete(String taskid, LeaveApply leave, String reapply) {
        Task task = taskservice.createTaskQuery().taskId(taskid).singleResult();
        String instanceid = task.getProcessInstanceId();
        ProcessInstance ins = runtimeservice.createProcessInstanceQuery().processInstanceId(instanceid).singleResult();
        String businesskey = ins.getBusinessKey();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("reapply", reapply);
        if (reapply.equals("true")) {
            LeaveApply a = leaveDao.load(Long.valueOf(businesskey));
            a.setLeaveType(leave.getLeaveType());
            a.setStartTime(leave.getStartTime());
            a.setEndTime(leave.getEndTime());
            a.setReason(leave.getReason());
            taskservice.complete(taskid, variables);
        } else
            taskservice.complete(taskid, variables);
    }

    public List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {

        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size(); i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得 到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            if ((i + 1) >= historicActivityInstances.size()) {
                break;
            }
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());// 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {// 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {// 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {// 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();// 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }
}
