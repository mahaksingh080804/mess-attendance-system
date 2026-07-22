package studentregistrationmodule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

                    Admin admin = new Admin(adminId, dbUsername, dbPassword);

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
}
