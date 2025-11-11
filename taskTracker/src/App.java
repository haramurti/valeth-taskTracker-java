import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String status1 ="to-do";
        String status2 ="in-progress";
        String status3 ="done";
        
        Scanner in = new Scanner(System.in);
        ArrayList<Task> listTasks = new ArrayList<Task>();
    
        while (true){

            String input = in.next();

        if (input.equals("add")){
            String desc = in.nextLine();
            Task task = new Task(desc);
            listTasks.add(task);
            System.out.println("success adding task with id : "+task.getId()+"\n description : "+desc);

        }else if (input.equals("update")){
            int idTujuan = in.nextInt();
            String updateInput = in.nextLine();
            System.out.println("success update task with id : "+listTasks.get(idTujuan).getId()+"\n description : "+updateInput);
            listTasks.get(idTujuan).setDesc(updateInput);

        }else if (input.equals("delete")){
            int idTujuan = in.nextInt();
            listTasks.remove(idTujuan);
            System.out.println("deleted task with id : "+idTujuan);

        }else if (input.equals("mark-in-progress")){
            int idTujuan = in.nextInt();
            listTasks.get(idTujuan).setStatus(status2);
            System.out.println("Task "+idTujuan+" marked as 'in-progress'");

        }else if (input.equals("mark-done")){
            int idTujuan = in.nextInt();
            listTasks.get(idTujuan).setStatus(status3);
            System.out.println("Task "+idTujuan+" marked as 'done'");

        }else if (input.equals("to-do")){
            int idTujuan = in.nextInt();
            listTasks.get(idTujuan).setStatus(status1);
            System.out.println("Task "+idTujuan+"marked as 'to-do'");

        }else if (input.equals("list")){
            for (int i = 0 ; i<listTasks.size();i++)
            listTasks.get(i).printAll();
        }
        in.close();
    }

    }
}

