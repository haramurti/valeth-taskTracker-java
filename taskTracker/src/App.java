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

        //update
        }else if (input.equals("update")){
            int idTujuan = in.nextInt();
            String updateInput = in.nextLine();
            boolean flag = false;
            for (int i = 0;i<listTasks.size();i++){;
                if(idTujuan == listTasks.get(i).getId()){
                    int id_target=listTasks.get(i).getId();
                    listTasks.get(i).setDesc(updateInput);;
                    System.out.println("succesfully updated task with id : "+id_target);
                    flag=true;
                    break;
                }
            }
            if (!flag){
                    System.out.println("Id not found");
                }

        //delete
        }else if (input.equals("delete")){
            int idTujuan = in.nextInt();
            boolean flag = false;
            for (int i = 0;i<listTasks.size();i++){;
                if(idTujuan == listTasks.get(i).getId()){
                    int id_target=listTasks.get(i).getId();
                    listTasks.remove(i);
                    System.out.println("succesfully deleted task with id : "+id_target);
                    flag=true;
                    break;
                }
            }
            if (!flag){
                    System.out.println("Id not found");
                }

        }else if (input.equals("mark-in-progress")){
            int idTujuan = in.nextInt();
            boolean flag = false;
            for (int i = 0;i<listTasks.size();i++){;
                if(idTujuan == listTasks.get(i).getId()){
                    int id_target=listTasks.get(i).getId();
                    listTasks.get(i).setStatus(status2);
                    System.out.println("Task "+idTujuan+" marked as 'in-progress'");
                    flag=true;
                    break;
                }
            }
            if (!flag){
                    System.out.println("Id not found");
                }
            

        }else if (input.equals("mark-done")){
            int idTujuan = in.nextInt();
            boolean flag = false;
            for (int i = 0;i<listTasks.size();i++){;
                if(idTujuan == listTasks.get(i).getId()){
                    int id_target=listTasks.get(i).getId();
                    listTasks.get(i).setStatus(status3);
                    System.out.println("Task "+id_target+" marked as 'done'");
                    flag=true;
                    break;
                }
            }
            if (!flag){
                    System.out.println("Id not found");
                }

        }else if (input.equals("to-do")){
            int idTujuan = in.nextInt();
            boolean flag = false;
            for (int i = 0;i<listTasks.size();i++){;
                if(idTujuan == listTasks.get(i).getId()){
                    int id_target=listTasks.get(i).getId();
                    listTasks.get(i).setStatus(status1);
                    System.out.println("Task "+id_target+" marked as 'to-do'");
                    flag=true;
                    break;
                }
            }
            if (!flag){
                    System.out.println("Id not found");
                }

        }else if (input.equals("list")){
            for (int i = 0 ; i<listTasks.size();i++)
            listTasks.get(i).printAll();
        }
    }

    }
}

