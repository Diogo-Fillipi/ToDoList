import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class DAO{
    public void createTask(Task task){
        Connection connection = DbTasks.getConnection();
        String sql = "INSERT INTO TASKS (namee, dueDate) VALUES (?,?)";

        try{
            String SQL = "SELECT task_id FROM Tasks";
            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet resultSet = ps.executeQuery();
            if(resultSet == null){
                String SQL2 = "UPDATE Tasks SET task_id = ?";
                PreparedStatement PS = connection.prepareStatement(SQL2);
                PS.setInt(1, 1);
                PS.executeUpdate();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            if(taskExistsInDatabase(task) == 0){
                try{
                    PreparedStatement PS = connection.prepareStatement(sql);
                    PS.setString(1, task.getDescription());
                    PS.setString(2, task.getDueDate());
                    PS.execute();
                    System.out.println("Task created with success!");
                    System.out.println("-------------------------------");

                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("Task already exist in database");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    //Method responsible for deleting the task from the database
    public void deleteTask() throws SQLException{
        List<Task> tasks= toListAll();
        Scanner inPut = new Scanner(System.in);
        System.out.println("Choose the task you want to delete according to its index");
        for(Task task : tasks ){
            System.out.println("ID da Tarefa: " + task.getTaskId());
            System.out.println("Descrição: " + task.getDescription());
            System.out.println("Data de Vencimento: " + task.getDueDate());
            System.out.println("-----------------------------");
        }
        int aux = inPut.nextInt();
        String deleteTaskSQL = "DELETE FROM Tasks WHERE task_id =?";
        try(PreparedStatement select = DbTasks.getConnection().prepareStatement(deleteTaskSQL)){
            select.setInt(1, aux);
            select.executeUpdate();
           // String updateOrderSQL = "UPDATE Tasks SET task_id = task_id - 1 WHERE task_id > ?";
            //PreparedStatement update = DbTasks.getConnection().prepareStatement(updateOrderSQL);
            //update.setInt(1,aux);
            //update.executeUpdate();


            //String fixIdString = "SELECT tasks_id FROM Tasks WHERE tasks_id = ?";
            //PreparedStatement fixId = DbTasks.getConnection().prepareStatement(fixIdString);
            //fixId.setInt(1, aux + 1);
            //ResultSet resultSett = fixId.executeQuery();
            //resultSett.next();
            //int fix = resultSett.getInt(1);

        }
    }
    public List<Task> toListAll(){
        List<Task> listTask = new ArrayList<>();
        Connection connection = DbTasks.getConnection();
        String sql = "SELECT * FROM Tasks";
        try{
            PreparedStatement PS = connection.prepareStatement(sql);
            ResultSet resultSet = PS.executeQuery();
            while(resultSet.next()){
                Task task = new Task(null, null);
                task.setTaskId(resultSet.getInt("task_id"));
                task.setDescription(resultSet.getString("namee"));
                task.setDueDate(resultSet.getString("dueDate"));

                listTask.add(task);
            }

            System.out.println("Choose the task you want to delete according to its index");
            for(Task task : listTask ){
                System.out.println("ID da Tarefa: " + task.getTaskId());
                System.out.println("Descrição: " + task.getDescription());
                System.out.println("Data de Vencimento: " + task.getDueDate());
                System.out.println("-----------------------------");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    return listTask;
    }

    public void toUpdateTask(){
        Connection connection = DbTasks.getConnection();
        String sql = "UPDATE Tasks SET ";
    }
    /*public void getTask() throws SQLException{
        String getTaskSQL = "SELECT * FROM Tasks";
        try(PreparedStatement getTaskSql = DbTasks.getConnection().prepareStatement(getTaskSQL)){
            ResultSet resultSet = getTaskSql.executeQuery();
            resultSet.next();
            int index = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String dueDate = resultSet.getString(3);
            taskManagerDB.toShowTasks(index, name, dueDate);
        }
    }*/
    //Method responsible for checking if the task already exists in the database.
    private int taskExistsInDatabase(Task task) throws SQLException {
        String checkIfExistsSQL = "SELECT COUNT(*) FROM Tasks WHERE namee = ?";
        try (PreparedStatement checkIfExistsPS = DbTasks.getConnection().prepareStatement(checkIfExistsSQL)) {
            checkIfExistsPS.setString(1, task.getDescription());
            ResultSet resultSet = checkIfExistsPS.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);


            return count; // If the count is greater than 0, the task is in the database already.
        }
    }
}
