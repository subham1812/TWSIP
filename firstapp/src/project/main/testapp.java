package project.main;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import project.utility.JdbcUtil;
public class testapp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection=null;
		PreparedStatement pstmt=null;
		ResultSet resultSet=null;
		try {
			connection=JdbcUtil.getDbConnection();
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter 1 to withdraw,2 to check balance,3 to deposit");
			int n=sc.nextInt();
			int a;
			if(n==1)
			{
				String sqlUpdateQuery="update person set ab=? where pin=?";
				if(connection!=null)
					pstmt=connection.prepareStatement(sqlUpdateQuery);
				if(pstmt!=null) {
					System.out.println("enter amount");
					int x=sc.nextInt();
					System.out.println("enter pin");
					int p=sc.nextInt();
					String str="select ab from person where pin="+p;
					resultSet=pstmt.executeQuery(str);
					resultSet.next();
					a=resultSet.getInt("ab")-x;
					if(a>0)
					{
						pstmt.setInt(1,a);
						pstmt.setInt(2, p);
						int rowAffected=pstmt.executeUpdate();
						if(rowAffected==1) {
							System.out.println("Cas Withdraw successfull");
						}
						else {
							System.out.println("pin not available");
						}
					}
					else
					{
						System.out.println("Not enough cash");
					}
				}
			}
			else if(n==2)
			{
					String sqlSelectQuery="select ab from person where pin=?";
					if(connection!=null)
						pstmt=connection.prepareStatement(sqlSelectQuery);
					if(pstmt!=null) {
						System.out.println("enter pin");
						int p=sc.nextInt();
						pstmt.setInt(1, p);
						resultSet =pstmt.executeQuery();
					if(resultSet!=null)
					{
						if(resultSet.next()) {
							System.out.println("Account balance");
							System.out.println(resultSet.getInt(1));
						}
					else
					{
						System.out.println("Record not available for the given pin:: "+p);
					}
			}
			}
			}
			else if(n==3)
			{
				String sqlUpdateQuery="update person set ab=? where pin=?";
				if(connection!=null)
					pstmt=connection.prepareStatement(sqlUpdateQuery);
				if(pstmt!=null) {
					System.out.println("enter amount");
					int x=sc.nextInt();
					System.out.println("enter pin");
					int p=sc.nextInt();
					String str="select ab from person where pin="+p;
					resultSet=pstmt.executeQuery(str);
					resultSet.next();
					a=resultSet.getInt("ab")+x;
						pstmt.setInt(1,a);
						pstmt.setInt(2, p);
						int rowAffected=pstmt.executeUpdate();
						if(rowAffected==1) {
							System.out.println("Cash Deposit successfull");
						}
						else {
							System.out.println("pin not available");
						}
					}
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				JdbcUtil.closeResources(resultSet,pstmt,connection);
			}catch(SQLException e)
			{
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
