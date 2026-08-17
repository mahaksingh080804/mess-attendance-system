package studentregistrationmodule;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportOperation {

    public ArrayList<DailyMealReport> getReport(LocalDate startDate , LocalDate endDate)  throws SQLException {

        ArrayList<DailyMealReport> reports = new ArrayList<>();

        String sql = "SELECT date, " +
                "COUNT(CASE WHEN breakfast = true THEN 1 END) AS breakfast_count, " +
                "COUNT(CASE WHEN lunch = true THEN 1 END) AS lunch_count, " +
                "COUNT(CASE WHEN dinner = true THEN 1 END) AS dinner_count " +
                "FROM Attendance " +
                "WHERE date BETWEEN ? AND ? " +
                "GROUP BY date " +
                "ORDER BY date";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setDate(1, java.sql.Date.valueOf(startDate));
            ps.setDate(2, java.sql.Date.valueOf(endDate));

            ResultSet rs = ps.executeQuery();

            while (rs.next()){

                LocalDate date = rs.getDate("date").toLocalDate();

                int breakfastCount = rs.getInt("breakfast_count");

                int lunchCount = rs.getInt("lunch_count");

                int dinnerCount = rs.getInt("dinner_count");

                DailyMealReport report=new DailyMealReport(date,breakfastCount,lunchCount,dinnerCount);

                reports.add(report);
            }
        }
        return reports;

    }

}
