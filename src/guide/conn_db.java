package guide;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection; 

public class conn_db {
	/**
	 * @author 王超凡
	 * 连接数据库
	 */
	Connection con;
	public static final String URL = "jdbc:mysql://localhost/person?serverTimezone=UTC&useSSL=false&user=root&password=root";
    public void connection() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    /*public static void main(String[] args){
		conn_db lo=new conn_db();

	}*/
}