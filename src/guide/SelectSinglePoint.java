package guide;
import java.sql.*;

public class SelectSinglePoint extends conn_db{
	/**
	 * @author 王超凡
	 * 通过int[] 节点列表返回double[][] x,y坐标列表
	 */
    private ResultSet rs;
    public double[][] SelectSinglePoint(int paths[]) throws SQLException{
		// TODO Auto-generated method stub
		try{
			connection();
		}catch(Exception ee){
			ee.printStackTrace();
		}
		for(int i=0;i<paths.length;i++){
			paths[i]+=1;
			//System.out.println(paths[i]);
		}
		
		Connection con=super.con;
		Statement stmt=con.createStatement();
		double[][] xy=new double[paths.length][2];
		for(int i=0;i<paths.length;i++){
			String sql = "SELECT x,y FROM view WHERE number='"+paths[i]+"'";
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				xy[i][0]=rs.getDouble(1);
				xy[i][1]=rs.getDouble(2);
				//System.out.println(xy[i][0]);
			}
		}  
        stmt.close();  
        rs.close();  
        con.close();  
        return xy;  
    }  		
}
