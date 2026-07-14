package studentregistrationmodule;

import java.sql.*;

public class AttendanceOperation {

    public boolean markAttendance(Attendance attendance, int mealChoice){
        int studentId= attendance.getStudentId();
        Date date= attendance.getDate();

        if(attendanceExists(studentId,date)){

            Attendance existingAttendance = getTodayAttendance(studentId, date);

            if (existingAttendance == null) {
                System.out.println("Attendance record not found.");
                return false;
            }

            switch (mealChoice){

                case 1:

                    if(existingAttendance.getBreakfast()){
                        System.out.println("Breakfast already marked.");
                        return false;
                    }

                    existingAttendance.setBreakfast(true);

                    break;

                case 2:

                    if(existingAttendance.getLunch()){
                        System.out.println("Lunch already marked.");
                        return false;
                    }
                    existingAttendance.setLunch(true);

                    break;

                case 3:

                    if(existingAttendance.getDinner()){
                        System.out.println("Dinner already marked.");
                        return false;
                    }
                    existingAttendance.setDinner(true);

                    break;

                default:
                    System.out.println("Invalid choice");
                    return false;
            }

            return updateAttendance(existingAttendance) == 1;

        }
        else {

            return insertAttendance(attendance) == 1;

        }
    }

    private boolean attendanceExists(int studentId,Date date){
            String sql="SELECT 1 FROM Attendance WHERE student_id= ? AND date= ?";

            try(
                    Connection con = DBConnection.getConnection();
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

    private Attendance getTodayAttendance(int studentId, Date date){
        String sql="SELECT attendance_id, student_id, date, breakfast, lunch, dinner " +
                   "FROM attendance WHERE student_id = ? AND date = ?";

        try(
                Connection con = DBConnection.getConnection();
                PreparedStatement ps=con.prepareStatement(sql);
        ){
            ps.setInt(1,studentId);
            ps.setDate(2,date);

            ResultSet rs =ps.executeQuery();

            if(rs.next()){
                int attendanceId = rs.getInt("attendance_id");
                int dbStudentId = rs.getInt("student_id");
                Date dbDate = rs.getDate("date");
                boolean breakfast = rs.getBoolean("breakfast");
                boolean lunch = rs.getBoolean("lunch");
                boolean dinner = rs.getBoolean("dinner");
                return new Attendance(
                        attendanceId,
                        dbStudentId,
                        dbDate,
                        breakfast,
                        lunch,
                        dinner
                );
            }

            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int updateAttendance(Attendance attendance){
        String sql="UPDATE Attendance SET breakfast= ?,lunch= ?,dinner= ? WHERE student_id= ? AND date= ?";
        try(
                Connection con = DBConnection.getConnection();
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
        try(
                Connection con = DBConnection.getConnection();
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
