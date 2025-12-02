
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {

    // Nama file database json kita
    private static final String FILE_NAME = "tasks.json";

    public static void main(String[] args) {

        // 1. LOAD DATA (Wajib di awal. Kalau ga, tiap run datanya kosong lagi)
        // Nanti lu implementasi logic parsingnya di method loadTasks() di bawah
        List<Task> listTasks = loadTasks();

        // Cek argumen (karena kita ga pake Scanner lagi)
        if (args.length < 1) {
            System.out.println("Usage: java App <command> [args]");
            return;
        }

        String command = args[0];

        // 2. ROUTING (Gantiin if-else scanner lu)
        switch (command) {
            case "add":
                if (args.length < 2) {
                    System.out.println("Error: Description required");
                    return;
                }
                String desc = args[1]; // Ambil dari argumen ke-2
                Task newTask = new Task(desc);
                // Set ID manual based on list size/max ID biar ga reset (PR lu buat nambahin logic ini)
                newTask.setId(listTasks.size() + 1);
                listTasks.add(newTask);
                System.out.println("Task added successfully (ID: " + newTask.getId() + ")");
                break;

            case "list":
                // Logic list lu udah bener
                if (listTasks.isEmpty()) {
                    System.out.println("No tasks found.");
                } else {
                    for (Task t : listTasks) {
                        t.printAll();
                    }
                }
                break;

            // ... (Tambahin case update, delete, mark-done disini copas logic lu yg lama) ...
            default:
                System.out.println("Unknown command: " + command);
                break;
        }

        // 3. SAVE DATA (Ini kuncinya! Sebelum program mati, simpan ke file)
        saveTasks(listTasks);
    }

    // --- INI SOLUSI DARI KEMALASANMU (JSON BUILDER) ---
    private static void saveTasks(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n"); // Buka Array JSON

        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            sb.append("  {\n");
            sb.append("    \"id\": ").append(t.getId()).append(",\n");
            // Escape quote kalo ada user iseng nulis "kutip" di deskripsi
            sb.append("    \"description\": \"").append(t.getDesc().replace("\"", "\\\"")).append("\",\n");
            sb.append("    \"status\": \"").append(t.getStatus()).append("\",\n");
            sb.append("    \"createdAt\": \"").append(t.getCreatedaAt()).append("\",\n");
            sb.append("    \"updatedAt\": \"").append(t.getUpdatedAt()).append("\"\n");
            sb.append("  }");

            // Kalo bukan item terakhir, kasih koma. JSON strict soal koma.
            if (i < tasks.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("]"); // Tutup Array JSON

        try {
            // Tulis string super panjang tadi ke file
            Files.writeString(Paths.get(FILE_NAME), sb.toString());
        } catch (IOException e) {
            System.out.println("Gagal nyimpen file bos: " + e.getMessage());
        }
    }

    // --- PR BUAT LU (LOGIC LOAD) ---
    private static List<Task> loadTasks() {
        List<Task> loadedTasks = new ArrayList<>();

        // Cek file ada ga?
        if (!Files.exists(Paths.get(FILE_NAME))) {
            return loadedTasks; // Return list kosong
        }

        try {
            String content = Files.readString(Paths.get(FILE_NAME));
            // NAH DISINI CHALLENGE-NYA:
            // Lu harus parsing string JSON tadi balik jadi Object Task.
            // Clue: Buang kurung [], split berdasarkan "}, {"
            // Tapi sementara return kosong dulu biar code-nya jalan.
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loadedTasks;
    }
}

// import java.util.ArrayList;
// import java.util.Scanner;
// public class App {
//     public void printtoJSON() {
//         System.out.println("printing into json");
//     }
//     //jadi intinya disini gua bakal bikin sebuah file task tracker yang bakal mencoba ngewritenya dalam java di mana nanti dia bakal ngewritek local gitu jadi kaya ktas ktracker lokal dulu gitu jadi nantni setelah itu bisa kita lakuin terus dan terus setleah itu mungkin bisa kita coba bikin pakai bahasa javanya adhuku hari ini kita coba buka aja diulu mari kita coba lanjut beosk
//     //I genuien ely still belive this is the most important and i have donw write, tis just soo lazy to make string builder for json files
//     public static void main(String[] args) throws Exception {
//         String status1 = "to-do";
//         String status2 = "in-progress";
//         String status3 = "done";
//         Scanner in = new Scanner(System.in);
//         ArrayList<Task> listTasks = new ArrayList<Task>();
//         while (true) {
//             String input = in.next();
//             //add
//             if (input.equals("add")) {
//                 in.nextLine();
//                 String desc = in.nextLine().trim();
//                 Task task = new Task(desc);
//                 listTasks.add(task);
//                 System.out.println("success adding task with id : " + task.getId() + "\n description : " + desc);
//                 //update
//             } else if (input.equals("update")) {
//                 int idTujuan = in.nextInt();
//                 String updateInput = in.nextLine();
//                 boolean flag = false;
//                 for (int i = 0; i < listTasks.size(); i++) {;
//                     if (idTujuan == listTasks.get(i).getId()) {
//                         int id_target = listTasks.get(i).getId();
//                         listTasks.get(i).setDesc(updateInput);;
//                         System.out.println("succesfully updated task with id : " + id_target);
//                         flag = true;
//                         break;
//                     }
//                 }
//                 if (!flag) {
//                     System.out.println("Id not found");
//                 }
//                 //delete
//             } else if (input.equals("delete")) {
//                 int idTujuan = in.nextInt();
//                 boolean flag = false;
//                 for (int i = 0; i < listTasks.size(); i++) {;
//                     if (idTujuan == listTasks.get(i).getId()) {
//                         int id_target = listTasks.get(i).getId();
//                         listTasks.remove(i);
//                         System.out.println("succesfully deleted task with id : " + id_target);
//                         flag = true;
//                         break;
//                     }
//                 }
//                 if (!flag) {
//                     System.out.println("Id not found");
//                 }
//             } else if (input.equals("mark-in-progress")) {
//                 int idTujuan = in.nextInt();
//                 boolean flag = false;
//                 for (int i = 0; i < listTasks.size(); i++) {;
//                     if (idTujuan == listTasks.get(i).getId()) {
//                         int id_target = listTasks.get(i).getId();
//                         listTasks.get(i).setStatus(status2);
//                         System.out.println("Task " + id_target + " marked as 'in-progress'");
//                         flag = true;
//                         break;
//                     }
//                 }
//                 if (!flag) {
//                     System.out.println("Id not found");
//                 }
//             } else if (input.equals("mark-done")) {
//                 int idTujuan = in.nextInt();
//                 boolean flag = false;
//                 for (int i = 0; i < listTasks.size(); i++) {;
//                     if (idTujuan == listTasks.get(i).getId()) {
//                         int id_target = listTasks.get(i).getId();
//                         listTasks.get(i).setStatus(status3);
//                         System.out.println("Task " + id_target + " marked as 'done'");
//                         flag = true;
//                         break;
//                     }
//                 }
//                 if (!flag) {
//                     System.out.println("Id not found");
//                 }
//             } else if (input.equals("to-do")) {
//                 int idTujuan = in.nextInt();
//                 boolean flag = false;
//                 for (int i = 0; i < listTasks.size(); i++) {;
//                     if (idTujuan == listTasks.get(i).getId()) {
//                         int id_target = listTasks.get(i).getId();
//                         listTasks.get(i).setStatus(status1);
//                         System.out.println("Task " + id_target + " marked as 'to-do'");
//                         flag = true;
//                         break;
//                     }
//                 }
//                 if (!flag) {
//                     System.out.println("Id not found");
//                 }
//             } else if (input.equals("list")) {
//                 for (int i = 0; i < listTasks.size(); i++) {
//                     listTasks.get(i).printAll();
//                 }
//             }
//         }
//     }
// }
