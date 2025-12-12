import java.util.ArrayList;
import java.util.Scanner;
import java.time.Instant;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
public class App {

    public static ArrayList<Task> listTasks = new ArrayList<Task>();


    public static void main(String[] args) throws Exception {
        String status1 = "to-do";
        String status2 = "in-progress";
        String status3 = "done";
        Scanner in = new Scanner(System.in);
        while (true) {
            String input = in.next();
            //add
            if (input.equals("add")) {
                in.nextLine();
                String desc = in.nextLine().trim();
                Task task = new Task(desc);
                listTasks.add(task);
                System.out.println("success adding task with id : " + task.getId() + "\n description : " + desc);

                saveDataJSON(listTasks);

                //update
            } else if (input.equals("update")) {
                int idTujuan = in.nextInt();
                in.nextLine();
                String updateInput = in.nextLine();
                boolean flag = false;
                for (int i = 0; i < listTasks.size(); i++) {;
                    if (idTujuan == listTasks.get(i).getId()) {
                        int id_target = listTasks.get(i).getId();
                        listTasks.get(i).setDesc(updateInput);;
                        System.out.println("succesfully updated task with id : " + id_target);
                        saveDataJSON(listTasks);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Id not found");
                }
                //delete
            } else if (input.equals("delete")) {
                int idTujuan = in.nextInt();
in.nextLine();
                boolean flag = false;
                for (int i = 0; i < listTasks.size(); i++) {;
                    if (idTujuan == listTasks.get(i).getId()) {
                        int id_target = listTasks.get(i).getId();
                        listTasks.remove(i);
                        System.out.println("succesfully deleted task with id : " + id_target);
                        saveDataJSON(listTasks);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Id not found");
                }
            } else if (input.equals("mark-in-progress")) {
                int idTujuan = in.nextInt();
in.nextLine();
                boolean flag = false;
                for (int i = 0; i < listTasks.size(); i++) {;
                    if (idTujuan == listTasks.get(i).getId()) {
                        int id_target = listTasks.get(i).getId();
                        listTasks.get(i).setStatus(status2);
                        System.out.println("Task " + id_target + " marked as 'in-progress'");
                        saveDataJSON(listTasks);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Id not found");
                }
            } else if (input.equals("mark-done")) {
                int idTujuan = in.nextInt();
in.nextLine();
                boolean flag = false;
                for (int i = 0; i < listTasks.size(); i++) {;
                    if (idTujuan == listTasks.get(i).getId()) {
                        int id_target = listTasks.get(i).getId();
                        listTasks.get(i).setStatus(status3);
                        System.out.println("Task " + id_target + " marked as 'done'");
                        saveDataJSON(listTasks);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Id not found");
                }
            } else if (input.equals("to-do")) {
                int idTujuan = in.nextInt();
in.nextLine();
                boolean flag = false;
                for (int i = 0; i < listTasks.size(); i++) {;
                    if (idTujuan == listTasks.get(i).getId()) {
                        int id_target = listTasks.get(i).getId();
                        listTasks.get(i).setStatus(status1);
                        System.out.println("Task " + id_target + " marked as 'to-do'");
                        saveDataJSON(listTasks);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Id not found");
                }
            } else if (input.equals("list")) {
                for (int i = 0; i < listTasks.size(); i++) {
                    listTasks.get(i).printAll();
                }
            }
        }
    }


    public static void saveDataJSON(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();

        sb.append("[\n");

        for (int i = 0; i< listTasks.size(); i++){

            Task t = listTasks.get(i);
            sb.append("  {\n");
            sb.append("    \"id\": ").append(t.getId()).append(",\n");

            String amanDesc = t.getDesc().replace("\"", "\\\"");
            sb.append("    \"description\": \"").append(amanDesc).append("\",\n");

            sb.append("    \"status\": \"").append(t.getStatus()).append("\",\n");

            sb.append("    \"CreatedAt\": \"").append(t.getCreatedaAt().toString()).append("\",\n");

            sb.append("    \"UpdatedAt\": \"").append(t.getUpdatedAt().toString()).append("\"\n");
            sb.append("  }");
        }
        sb.append("]");

        try {
            Files.writeString(Paths.get("tasks.json"),sb.toString());
            System.out.println("succesfully saved");
        }catch (IOException e ){
            System.out.println("failed save file " + e.getMessage());
        }

    }
}
