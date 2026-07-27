package studentregistrationmodule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminOperation {

    public Admin login(String username, String password) {

        String sql =
                "SELECT * FROM Admin WHERE username = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
        ) {
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int adminId = rs.getInt("admin_id");
                String dbUsername = rs.getString("username");
                String dbPassword = rs.getString("password");

                if (dbPassword.equals(password)) {

                    Admin admin = new Admin(adminId,
                            dbUsername,
                            dbPassword);

                    return admin;
                }
                return null;
            }
            return null;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;

        }
    }

    public ArrayList<Student> viewStudents() {

        ArrayList<Student> studentList = new ArrayList<>();

        String sql =
                "SELECT student_id, student_name, email FROM Student";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {

            while (rs.next()) {

                int dbStudentId = rs.getInt("student_id");

                String dbStudentName = rs.getString("student_name");

                String dbStudentEmail = rs.getString("email");

                Student student = new Student(dbStudentId,
                        dbStudentName,
                        dbStudentEmail);

                studentList.add(student);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }

}
