package sdemoalarmmanager.edu.rp.c347.taskmanager;

import java.io.Serializable;

/**
 * Created by 15017466 on 26/5/2017.
 */

public class Task implements Serializable {

    private int id;
    private String TaskName;
    private String Description;

    public Task(int id, String TaskName,String Description) {
        this.id = id;
        this.TaskName = TaskName;
    }

    public int getId() {
        return id;
    }

    public String getTaskName() {
        return TaskName;
    }

    public String getDescription() {
        return Description;
    }

    public void setTaskName(String TaskName) {
        this.TaskName = TaskName;

    }

    public void setDescription(String Description) {
        this.Description = Description;

    }

    public String toString() {
        return "ID:" + id + ", " + TaskName + "," + Description;
    }
}