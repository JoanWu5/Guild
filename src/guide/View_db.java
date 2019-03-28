package guide;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class View_db extends conn_db implements ActionListener,ItemListener{
	/**
	 * @author 王超凡
	 * 景点数据数据库，返回景点名字，介绍，节点号码
	 */
	private JButton confirm,back;
	private ResultSet rs;
	private Choice box;
	int nu;
	String na,des;
	
	public void setButtons(JButton b1,JButton b2){
		confirm=b1;
		back=b2;
	}
	
	public void setChoice(Choice c){
		box=c;
	}
	
	@Override
	public void itemStateChanged(ItemEvent ie) {
		// TODO Auto-generated method stub
		String item=box.getSelectedItem();
		try{
			connection();
			boolean com=SetWithSql(item);
		}catch(Exception ee){
			ee.printStackTrace();
		}
	}
	
	public boolean SetWithSql(String item) throws SQLException {
		// TODO Auto-generated method stub
		String sql;
		Connection con=super.con;
		Statement stmt=con.createStatement();
		sql="SELECT number,name,description FROM view WHERE name='"+item+"'";
		rs=stmt.executeQuery(sql);
		while(rs.next()){
			nu=rs.getInt(1);
			na=rs.getString(2);
			des=rs.getString(3);
			
			return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==confirm){
			try {
				new FindView(nu,na,des);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(ae.getSource()==back){
			new MainMenu();
		}
	}

}
