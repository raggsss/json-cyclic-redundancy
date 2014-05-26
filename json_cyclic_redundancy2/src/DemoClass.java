import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DemoClass {

	public static void main(String[] args) {
		
		String url = "jdbc:mysql://finch:3306/asset_management";
		String username = "root";
		String pass = "finch123";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url, username, pass);
			System.out.println(!connection.isClosed());
			
			String sql = "select * from employee";
			Statement statement = connection.createStatement();
			ResultSet result = statement.getResultSet();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}