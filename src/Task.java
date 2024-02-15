public class Task {
    //Attributes//
    private String description;
    private boolean done;
    private String dueDate;
    private int taskId;
    //Methods//
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String descMod){
        this.description = descMod;
    }
    public boolean getDone(){
        return this.done;
    }
    public void setDone(boolean doneMod){
        this.done = doneMod;
    }

    public String getDueDate(){
        return this.dueDate;
    }
    public void setDueDate(String dueDateMod){
        this.dueDate = dueDateMod;
    }
    public int getTaskId(){
        return this.taskId;
    }
    public void setTaskId(int taskId){
        this.taskId = taskId;
    }
    //Constructor//
    public Task(String desc, String date){

        this.description = desc;
        this.done = false;
        this.dueDate = date;

    }


}
