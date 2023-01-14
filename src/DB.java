import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	private static Connection conn =null;
	private static Statement stmt;
	private static ResultSet rs = null;
	private PreparedStatement pstmt;
	
	//DB연결
	public static void init(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/project202045016?serverTimezone=UTC",
					"root", 
					"1111");
		
			stmt = conn.createStatement();
			
		} catch (ClassNotFoundException e) {
			System.out.println("예외발생 : 해당 드라이버가 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("예외발생 : 접속 정보 확인이 필요합니다.");
			e.printStackTrace();
		}
	}
	
	//2. getResultSet() - 쿼리 결과 집합 가져오기
		public static ResultSet getResultSet(String sql) throws Exception 
		{
			try {
				stmt = conn.createStatement();
				return stmt.executeQuery(sql);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new Exception("쿼리 오류(getResultSet) : " + sql);
			}
		}
		
		//3. executeQuery() - 쿼리 수행하기
		public static void executeQuery(String sql) throws Exception {
			try {
				stmt.executeUpdate(sql);
			} catch (Exception e) {
				// TODO: handle exception
				throw new Exception("쿼리 오류(executeQuery) : " + sql);
			}
		}
	
}
