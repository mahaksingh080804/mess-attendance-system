package studentregistrationmodule;

import java.sql.*;

public class StudentOperation {

    public boolean emailExists(String email){
        String sql=
                "SELECT * FROM Student WHERE email=?";
        try(
                Connection con=DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/mess_food_waste_predictor",
                        "root",
                        "password"
                );
                PreparedStatement ps=con.prepareStatement(sql);
                ){
            ps.setString(1,email);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                return true;
            }
                return false;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public int registerStudent(Student student) {
        String sql =
                "INSERT INTO Student(student_name,email,password) VALUES(?,?,?)";
        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/mess_food_waste_predictor",
                        "root",
                        "password"
                );
                PreparedStatement ps = con.prepareStatement(sql);

        ) {
            ps.setString(1, student.getStudentName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPassword());

            int rows = ps.executeUpdate();

            return rows;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public boolean login(String email,String password) {
        String sql =
                "SELECT * FROM Student WHERE email=?";
        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/mess_food_waste_predictor",
                        "root",
                        "password"
                );
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String storedPassword = rs.getString("password");

                if (storedPassword.equals(password)) {
                    return true;
                }
                return false;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
