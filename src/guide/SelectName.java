package guide;

import java.sql.*;

public class SelectName extends conn_db{
	/**
	 * @author 王超凡
	 * choice=0,选择景点的所有名字，choice=1,选择特定点的名字，返回String[] 名字列表
	 */
	private ResultSet rs;
	private String[] names=new String[57];
	private int num=0;
	public String[] SelectName(int[] numbers,int choice) throws SQLException{
		if(numbers[0]==-1)
			return null;
		else{
			try{
				connection();
			}catch(Exception ee){
				ee.printStackTrace();
			}
			Connection con=super.con;
			Statement stmt=con.createStatement();
			if(choice==0){
				while(numbers[num]!=-1&&numbers[num]<=42){
					String sql = "SELECT name FROM view WHERE number='"+numbers[num]+"'";
					rs=stmt.executeQuery(sql);
					while(rs.next()){
						names[num]=rs.getString(1);
					}
					num++;	
				}
			}
			else if(choice==1){
				for(num=0;num<numbers.length;num++){
					String sql = "SELECT name FROM view WHERE number='"+numbers[num]+"'";
					rs=stmt.executeQuery(sql);
					while(rs.next()){
						names[num]=rs.getString(1);
					}
				}
			}
			return names;
		}
		
	}
}
