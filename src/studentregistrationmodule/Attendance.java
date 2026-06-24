package studentregistrationmodule;
import java.sql.Date;

public class Attendance {
    private int attendanceId;
    private int studentId;
    private Date date;
    private boolean breakfast;
    private boolean lunch;
    private boolean dinner;

    public Attendance(int studentId,Date date,boolean breakfast, boolean lunch, boolean dinner){
        this.studentId=studentId;
        this.date=date;
        this.breakfast=breakfast;
        this.lunch=lunch;
        this.dinner=dinner;
    }

    public int getAttendanceId(){
        return attendanceId;
    }

    public int getStudentId(){
        return studentId;
    }

    public Date getDate(){
        return date;
    }

    public boolean getBreakfast(){
        return breakfast;
    }

    public boolean getLunch(){
        return lunch;
    }

    public boolean getDinner(){
        return dinner;
    }
}
