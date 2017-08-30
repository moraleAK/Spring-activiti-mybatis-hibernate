package entity;

import java.io.Serializable;

import org.activiti.engine.task.Task;

import javax.persistence.*;

@Entity
@Table(name = "leave_apply")
public class LeaveApply implements Serializable {
    @Id
    @GeneratedValue
    long id;
    @Column(name = "processInstanceId")
    String processInstanceId;
    @Column(name = "user_id")
    String userId;
    @Column(name = "startTime")
    String startTime;
    @Column(name = "endTime")
    String endTime;
    @Column(name = "leaveType")
    String leaveType;
    @Column(name = "reason")
    String reason;
    @Column(name = "applyTime")
    String applyTime;
    @Column(name = "realityStartTime")
    String realityStartTime;
    @Column(name = "realityEndTime")
    String realityEndTime;
    @Transient
    Task task;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getRealityStartTime() {
        return realityStartTime;
    }

    public void setRealityStartTime(String realityStartTime) {
        this.realityStartTime = realityStartTime;
    }

    public String getRealityEndTime() {
        return realityEndTime;
    }

    public void setRealityEndTime(String realityEndTime) {
        this.realityEndTime = realityEndTime;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
