import java.sql.SQLException;
import java.util.Scanner;
public class Main {


    public static void main(String[] args) throws SQLException {
        TasksList taskManager = new TasksList();
        int i = 0;
        Scanner inPut = new Scanner(System.in);
        System.out.println("-------------------------------");
        System.out.println("|Be welcome to the To-do list!|");
        System.out.println("-------------------------------");
        while (i != 5) {

            System.out.println("What do you wanna do?");
            System.out.println("[1] Create a new task\n[2] Delete a task\n[3] Show the current tasks\n[4] Change the task informations\n[5] End the to-do list");
            int x = inPut.nextInt();


            switch (x) {
                case 1:
                    System.out.println("Type the task name: ");
                    inPut.nextLine();
                    String name = inPut.nextLine();
                    System.out.println("Type the task due date: ");
                    String date = inPut.nextLine();
                    taskManager.toAddTask(name, date);
                    break;
                case 2:
                    System.out.println("Which task do you want to delete?\nType the number of the task below");
                    taskManager.tasksToDelete();
                    System.out.println("-------------------------------");
                    break;
                case 3:
                    taskManager.toShowTasks();
                    System.out.println("-------------------------------");
                    break;
                case 4:
                    System.out.println("Choose a task according to its index to change its informations.");
                    //taskManager.toShowTasks();
                    int y = inPut.nextInt();
                    inPut.nextLine();
                    taskManager.toSaveList(y);
                    break;

                case 5:
                    System.out.println("-------------------------------");
                    System.out.println("|     Ending the program...   |");
                    System.out.println("-------------------------------");
                    i = 5;
                    break;

            }


        }
    }
}




