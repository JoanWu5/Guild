package guide;

import java.sql.*;


public class SelectXY extends conn_db{ 
	/**
	 * @author 王超凡
	 * 返回列表的所有x,y坐标
	 */
    private int k = 2;
    private ResultSet rs;
	public double[][] getxy() throws SQLException{
		// TODO Auto-generated method stub
		try{
			connection();
		}catch(Exception ee){
			ee.printStackTrace();
		}
		Connection con=super.con;
		Statement stmt=con.createStatement();
		String sql = "SELECT x,y FROM view";
		rs=stmt.executeQuery(sql);
		int sample = 0;  
        while (rs.next()) {  
            sample++;  
        }  
        double[][]xy=new double[sample][k];
        rs = stmt.executeQuery(sql);
        for (int i = 0; rs.next(); i++) {  
            for (int j = 0; j < k; j++) {  
                xy[i][j] = rs.getDouble(j+1);  
            }  
        }   
        stmt.close();  
        rs.close();  
        con.close();  
        return xy;  
    }  		
}

