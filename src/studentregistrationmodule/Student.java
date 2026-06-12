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


