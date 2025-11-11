import java.time.LocalDateTime;

public class Task {

    //atributes of the Task
    private int ID;
    static int ID_counter = 0;
    private String Description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //constructors

    public Task(String Description){
        this.ID =ID_counter;
        this.Description = Description ;
        this.status = "to-do" ;
        this.createdAt = LocalDateTime.now() ;
        this.updatedAt = LocalDateTime.now() ;

        ID_counter++;
    }

    //setter and getter

    public int getId(){
        return this.ID;
    }

    public void setId(int id){
        this.ID = id ;
        this.updatedAt = LocalDateTime.now();
    }

    public String getDesc(){
        return this.Description;
    }

    public void setDesc(String Desc){
        this.Description = Desc;
        this.updatedAt = LocalDateTime.now();
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
        this.updatedAt = LocalDateTime.now();
    }

    //getting local created at nad updated att

    public LocalDateTime getCreatedaAt(){
        return this.createdAt;
    }

    public LocalDateTime getUpdatedAt(){
        return this.updatedAt;
    }

    //method printing all
    public void printAll(){
        System.out.println("============"+Description+"============");
        System.out.println("Id : "+this.ID);
        System.out.println("Description : "+this.Description);
        System.out.println("Status : "+this.status);
        System.out.println("Created At : "+this.createdAt);
        System.out.println("Updated At : "+this.updatedAt);
        System.out.println("=================================");
        System.out.println();
    }

}
