import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AirlineData {

	private static Connection conn = null;
	private ResultSet rs;

	public AirlineData() throws SQLException {
		String url = "jdbc:mysql://localhost/Airport";
		String uname = "root";
		String pwd = "root";
		conn = DriverManager.getConnection(url, uname, pwd);
		System.out.println("Connnected to DB...");
	}
	public void displayRecords(String CODE) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement("Select * from AIRPORT_INFO Where PLANE_CODE =  '" + CODE+"'");

		rs = stmt.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString("PLANE_CODE") + "\t " + rs.getString("PLANE_NAME") + "\t "
					+ rs.getString("SOURCE") + " \t" + rs.getString("DESTINATION") + "\t " + rs.getDate("DATE") + "\t "
					+ rs.getTime("ARRIVAL") + "\t " + rs.getTime("DEPARTURE") + " \t" + rs.getString("STATUS") + "\t "
					+ rs.getTime("DURATION") + "\t " + rs.getTime("IDLE_TIME") + "\t " + rs.getInt("NO_OF_STOPS"));
		}
		stmt.close();
	}

	public static void main(String[] args) throws SQLException {

		AirlineData AD = new AirlineData();
		System.out.println("Enter CODE of PLANE :");
		Scanner sc = new Scanner(System.in);
		String PLANE_CODE = sc.next();
		sc.close();
		AD.displayRecords(PLANE_CODE);
		conn.close();
	}
}
