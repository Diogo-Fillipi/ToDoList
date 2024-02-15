import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TasksList implements Application {
    //Attributes//
    protected List<Task> tasks;

    //Getters & Setters//
    //Constructor//

    //Creat an ArrayList using the import "Java.util.ArrayList" and the "Java.util.List"
    public TasksList(){
        this.tasks = new ArrayList<>();
    }

    @Override
    public void toAddTask(String name, String dueDate) {
        DAO db = new DAO();
        Task task = new Task(name, dueDate);
        db.createTask(task);


    }

    @Override
    public void toRemoveTask(int index) {


    }

    @Override
    public void tasksToDelete(){
        System.out.println("Choose the task you want to delete according to its index");
        DAO db = new DAO();
        try{
            db.deleteTask();
        }catch(SQLException e){
            e.printStackTrace();
        }



    }

    @Override
    public void toShowTasks() {
        DAO db = new DAO();
        db.toListAll();

    }

    @Override
    public void toMarkAsDone() {

    }

    @Override
    public void dueDate() {
    }

    @Override
    public void toSaveList(int index) {
        Scanner inPut = new Scanner(System.in);
        System.out.println("The task selected is:\n" + tasks.get(index).getDescription());
        System.out.println("Type [1] if you want to change the name or [2] if you want to change the due date.");
        int x = inPut.nextInt();
        switch(x){
            case 1:
                System.out.println("Type the new name for the task");
                inPut.nextLine();
                String nameMod = inPut.nextLine();
                tasks.get(index).setDescription(nameMod);
                System.out.println("Task name changed with success!");
                System.out.println("-------------------------------");
                break;
            case 2:
                System.out.println("Type the new due date for th    e task");
                inPut.nextLine();
                String dueDateMod = inPut.nextLine();
                tasks.get(index).setDueDate(dueDateMod);
                System.out.println("Task due date changed with success!");
                System.out.println("-------------------------------");
                break;
        }

    }
}

