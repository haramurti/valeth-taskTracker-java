
import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

public class App {

    public void printtoJSON() {
        System.out.println("printing into json");
    }

    //jadi intinya disini gua bakal bikin sebuah file task tracker yang bakal mencoba ngewritenya dalam java di mana nanti dia bakal ngewritek local gitu jadi kaya ktas ktracker lokal dulu gitu jadi nantni setelah itu bisa kita lakuin terus dan terus setleah itu mungkin bisa kita coba bikin pakai bahasa javanya adhuku hari ini kita coba buka aja diulu mari kita coba lanjut beosk
    public static void main(String[] args) throws Exception {
        String status1 = "to-do";
        String status2 = "in-progress";
        String status3 = "done";

        Scanner in = new Scanner(System.in);
        ArrayList<Task> listTasks = new ArrayList<Task>();
        JsonObjectBuilder taskBuilder = Json.createObjectBuilder();

        while (true) {

            String input = in.next();

            //add
            if (input.equals("add")) {
                in.nextLine();
                String desc = in.nextLine().trim();
                Task task = new Task(desc);
                listTasks.add(task);
                System.out.println("success adding task with id : " + task.getId() + "\n description : " + desc);

                //update
            } else if (input.equals("update")) {
                int idTujuan = in.nextInt();
                String updateInput = in.nextLine();
                boolean flag = false;
                for (int i = 0; i < listTasks.size(); i++) {;
                    if (idTujuan == listTasks.get(i).getId()) {
                        int id_target = listTasks.get(i).getId();
                        listTasks.get(i).setDesc(updateInput);;
                        System.out.println("succesfully updated task with id : " + id_target);
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
                boolean flag = false;
                for (int i = 0; i < listTasks.size(); i++) {;
                    if (idTujuan == listTasks.get(i).getId()) {
                        int id_target = listTasks.get(i).getId();
                        listTasks.remove(i);
                        System.out.println("succesfully deleted task with id : " + id_target);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Id not found");
                }

            } else if (input.equals("mark-in-progress")) {
                int idTujuan = in.nextInt();
                boolean flag = false;
                for (int i = 0; i < listTasks.size(); i++) {;
                    if (idTujuan == listTasks.get(i).getId()) {
                        int id_target = listTasks.get(i).getId();
                        listTasks.get(i).setStatus(status2);
                        System.out.println("Task " + id_target + " marked as 'in-progress'");
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Id not found");
                }

            } else if (input.equals("mark-done")) {
                int idTujuan = in.nextInt();
                boolean flag = false;
                for (int i = 0; i < listTasks.size(); i++) {;
                    if (idTujuan == listTasks.get(i).getId()) {
                        int id_target = listTasks.get(i).getId();
                        listTasks.get(i).setStatus(status3);
                        System.out.println("Task " + id_target + " marked as 'done'");
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("Id not found");
                }

            } else if (input.equals("to-do")) {
                int idTujuan = in.nextInt();
                boolean flag = false;
                for (int i = 0; i < listTasks.size(); i++) {;
                    if (idTujuan == listTasks.get(i).getId()) {
                        int id_target = listTasks.get(i).getId();
                        listTasks.get(i).setStatus(status1);
                        System.out.println("Task " + id_target + " marked as 'to-do'");
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
}
