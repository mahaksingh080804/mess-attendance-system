package studentregistrationmodule;

import java.sql.*;

public class AttendanceOperation {

    public void markAttendance(Attendance attendance){
        int studentId= attendance.getStudentId();
        Date date= attendance.getDate();

        if(attendanceExists(studentId,date)){
            updateAttendance(attendance);
        }
        else {
            insertAttendance(attendance);
        }
    }

    private boolean attendanceExists(int studentId,Date date){
            String sql="SELECT 1 FROM Attendance WHERE student_id= ? AND date= ?";

            try(Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mess_food_waste_predictor",
                    "root",
                    "password"
            );
                PreparedStatement ps=con.prepareStatement(sql);
            ){
                ps.setInt(1,studentId);
                ps.setDate(2,date);

                ResultSet rs= ps.executeQuery();

                return rs.next();
            }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int updateAttendance(Attendance attendance){
        String sql="UPDATE Attendance SET breakfast= ?,lunch= ?,dinner= ? WHERE student_id= ? AND date= ?";
        try(Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mess_food_waste_predictor",
                "root",
                "password"
        );
            PreparedStatement ps=con.prepareStatement(sql);
            ){
            ps.setBoolean(1,attendance.getBreakfast());
            ps.setBoolean(2,attendance.getLunch());
            ps.setBoolean(3,attendance.getDinner());
            ps.setInt(4,attendance.getStudentId());
            ps.setDate(5,attendance.getDate());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private int insertAttendance(Attendance attendance){
        String sql="INSERT INTO Attendance(student_id,date,breakfast,lunch,dinner) VALUES(?,?,?,?,?)";
        try(Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mess_food_waste_predictor",
                "root",
                "password"
        );
            PreparedStatement ps=con.prepareStatement(sql);
        ){
            ps.setInt(1,attendance.getStudentId());
            ps.setDate(2,attendance.getDate());
            ps.setBoolean(3,attendance.getBreakfast());
            ps.setBoolean(4,attendance.getLunch());
            ps.setBoolean(5,attendance.getDinner());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
