
import java.util.ArrayList;
import java.util.Scanner;
import java.time.Instant;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

public class App {

    public static ArrayList<Task> listTasks = new ArrayList<Task>();

    public static void loadDataJSON() {
        try {
            //check file exist
            if (!Files.exists(Paths.get("tasks.json"))) {
                return;
            }

            String content = Files.readString(Paths.get("tasks.json"));
            content = content.trim().replace("[", "").replace("]", "");

            if (content.isEmpty()) {
                return;
            }

            String[] tasksRaw = content.split("},");

            for (String tRaw : tasksRaw) {

                int idIndex = tRaw.indexOf("\"id\":") + 5;
                int endId = tRaw.indexOf(",", idIndex);
                int id = Integer.parseInt(tRaw.substring(idIndex, endId).trim());

                int descIndex = tRaw.indexOf("\"description\": \"") + 16;
                int endDesc = tRaw.indexOf("\",", descIndex);
                String desc = tRaw.substring(descIndex, endDesc);

                int statIndex = tRaw.indexOf("\"status\": \"") + 11;
                int endStat = tRaw.indexOf("\",", statIndex);
                String status = tRaw.substring(statIndex, endStat);

                Task t = new Task(desc);
                t.setId(id);
                t.setStatus(status);

                listTasks.add(t);
            }
            System.out.println("data loaded!");

        } catch (Exception e) {
            System.out.println("Failed load data (not suitable JSON format): " + e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        String status1 = "to-do";
        String status2 = "in-progress";
        String status3 = "done";

        loadDataJSON();

        if (args.length == 0) {
            System.out.println("please input cmd");
            return;
        }

        String input = args[0];

        //add
        if (input.equals("add")) {
            if (args.length < 2) {
                System.out.println("failed : please input descirption");
            }
            String desc = args[1];
            Task task = new Task(desc);

            int newId = 1;
            if (!listTasks.isEmpty()) {
                newId = listTasks.get(listTasks.size() - 1).getId() + 1;
            }

            task.setId(newId);
            listTasks.add(task);
            System.out.println("success adding task with id : " + task.getId());
            System.out.println("description : " + desc);

            saveDataJSON(listTasks);

            //update
        } else if (input.equals("update")) {
            if (args.length < 3) {
                System.out.println("Failed: update format wrong. Use: update [id] [Desc]");
                return;
            }
            int idTujuan = Integer.parseInt(args[1]);
            String updateInput = args[2];
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
            if (args.length < 2) {
                System.out.println("Failed: need ID?");
                return;
            }

            int idTujuan = Integer.parseInt(args[1]);
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
            int idTujuan = Integer.parseInt(args[1]);
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
            int idTujuan = Integer.parseInt(args[1]);
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
            int idTujuan = Integer.parseInt(args[1]);
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

    //pembuatan file to JSON
    public static void saveDataJSON(ArrayList<Task> tasks) {
        StringBuilder sb = new StringBuilder();

        sb.append("[\n");

        for (int i = 0; i < listTasks.size(); i++) {

            Task t = listTasks.get(i);
            sb.append("  {\n");
            sb.append("    \"id\": ").append(t.getId()).append(",\n");

            String amanDesc = t.getDesc().replace("\"", "\\\"");
            sb.append("    \"description\": \"").append(amanDesc).append("\",\n");

            sb.append("    \"status\": \"").append(t.getStatus()).append("\",\n");

            sb.append("    \"CreatedAt\": \"").append(t.getCreatedaAt().toString()).append("\",\n");

            sb.append("    \"UpdatedAt\": \"").append(t.getUpdatedAt().toString()).append("\"\n");
            sb.append("  }");

            //added to check if it was the last task
            if (i < tasks.size() - 1) {
                sb.append(",");
            }

            sb.append("\n");
        }
        sb.append("]");

        try {
            Files.writeString(Paths.get("tasks.json"), sb.toString());
            System.out.println("succesfully saved");
        } catch (IOException e) {
            System.out.println("failed save file " + e.getMessage());
        }

    }
}
