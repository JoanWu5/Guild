package guide;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Route_db extends conn_db implements ActionListener,ItemListener{
	/**
	 * @author 王超凡
	 * 路线查询连接数据库，通过选择的起点和终点，找到对应的节点的号码，弹出路线查询结果界面
	 */
	private JButton confirm,back;
	private ResultSet rs;
	private Choice from,to;
	int num1,num2;
	String item1,item2;
	
	public void setButtons(JButton b1,JButton b2){
		confirm=b1;
		back=b2;
	}
	
	public void setChoices(Choice c,Choice d){
		from=c;
		to=d;
	}
	
	
	@Override
	public void itemStateChanged(ItemEvent ie) {
		// TODO Auto-generated method stub
		if(ie.getSource()==to){
			item1=from.getSelectedItem();	
			item2=to.getSelectedItem();
			try{
				connection();
				boolean com=GetFromSqls(item1,item2);
			}catch(Exception ee){
				ee.printStackTrace();
			}
		}
	}

	private boolean GetFromSqls(String item1, String item2) throws SQLException {
		// TODO Auto-generated method stub
		String sql;
		Connection con=super.con;
		Statement stmt=con.createStatement();
		sql="SELECT number FROM view WHERE name='"+item1+"'";
		rs=stmt.executeQuery(sql);
		while(rs.next()){
			num1=rs.getInt(1);
		}
		sql="SELECT number FROM view WHERE name='"+item2+"'";
		rs=stmt.executeQuery(sql);
		while(rs.next()){
			num2=rs.getInt(1);
			return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==confirm){
			try {
				new FindRoute(num1,num2,item1,item2);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(ae.getSource()==back){
			new MainMenu();
		}
	}

}
