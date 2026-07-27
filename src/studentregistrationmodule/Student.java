package studentregistrationmodule;

public class Student {

    private int studentId;
    private String studentName;
    private String email;
    private String password;


    public Student(String studentName, String email, String password) {
        this.studentName = studentName;
        this.email = email;
        this.password = password;
    }


    public Student(int studentId, String studentName, String email, String password) {
        this.studentId=studentId;
        this.studentName = studentName;
        this.email = email;
        this.password = password;
    }

    public Student(int studentId,
                   String studentName,
                   String email) {

        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
    }


    public int getStudentId(){
        return studentId;
    }


    public String getStudentName(){
        return studentName;
    }


    public String getEmail(){
        return email;
    }


    public String getPassword(){
        return password;
    }

}


