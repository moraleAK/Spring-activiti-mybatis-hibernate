package entity;

import java.util.Date;

import org.activiti.engine.task.Task;

import javax.persistence.*;

@Entity
@Table(name = "purchase_apply")
public class PurchaseApply {
    @Id
    @GeneratedValue
    long id;
    String itemList;
    float total;
    Date applyTime;
    String applyer;
    @Transient
    Task task;

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemList() {
        return itemList;
    }

    public void setItemList(String itemList) {
        this.itemList = itemList;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
