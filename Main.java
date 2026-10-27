
import java.sql.*;

public class CollegeReport {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/college",
                "root", "your_password");

            Statement st = con.createStatement();
            ResultSet rs;

            System.out.println("STUDENTS PER DEPARTMENT");
            rs = st.executeQuery(
                "SELECT d.dept_name, COUNT(s.id) AS total " +
                "FROM department d LEFT JOIN student s " +
                "ON d.dept_id=s.dept_id " +
                "GROUP BY d.dept_id,d.dept_name");

            while (rs.next())
                System.out.println(rs.getString(1) +
                    " : " + rs.getInt(2));

            rs = st.executeQuery(
                "SELECT d.dept_name,COUNT(s.id) AS total " +
                "FROM department d JOIN student s " +
                "ON d.dept_id=s.dept_id " +
                "GROUP BY d.dept_id,d.dept_name " +
                "ORDER BY total DESC LIMIT 1");

            if (rs.next())
                System.out.println("\nMost Students: " +
                    rs.getString(1) + " (" + rs.getInt(2) + ")");

            System.out.println("\nSTUDENTS DEPARTMENT-WISE");
            rs = st.executeQuery(
                "SELECT d.dept_name,s.id,s.name " +
                "FROM department d JOIN student s " +
                "ON d.dept_id=s.dept_id " +
                "ORDER BY d.dept_name,s.name");

            while (rs.next())
                System.out.println(rs.getString(1) + " - " +
                    rs.getInt(2) + " - " + rs.getString(3));

            con.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
