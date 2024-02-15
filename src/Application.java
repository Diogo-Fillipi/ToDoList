public interface Application {
    public abstract void toAddTask(String name, String date);
    public abstract void toRemoveTask(int index);
    public abstract void toShowTasks();
    public abstract void toMarkAsDone();
    public abstract void dueDate();
    public abstract void toSaveList(int index);
    public abstract void tasksToDelete();
}

