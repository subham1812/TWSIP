package project.utility;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JdbcUtil {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getDbConnection() throws SQLException{
		String url="jdbc:mysql://localhost:3306/atm";
		String user="root";
		String password="root123";
		return DriverManager.getConnection(url,user,password);
	}
	public static void closeResources(ResultSet resultSet,Statement statement,Connection connection) throws SQLException{
		if(resultSet!=null)
			resultSet.close();
		if(statement!=null)
			statement.close();
		if(connection!=null)
			connection.close();
	}
}
