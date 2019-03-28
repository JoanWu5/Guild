package guide;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class Travel_db extends conn_db implements ActionListener,ItemListener{
	/**
	 * @author 王超凡
	 * 游览推荐路线连接数据库
	 */
	private int x=50,y=120;
	private Choice start;
	private JButton confirm,back;
	private ResultSet rs;
	String item;
	private int num;
	Image map=Toolkit.getDefaultToolkit().getImage("images\\map.png");
	
	public void setButtons(JButton b1,JButton b2){
		confirm=b1;
		back=b2;
	}
	
	public void setChoice(Choice c){
		start=c;
	}
	
	
	@Override
	public void itemStateChanged(ItemEvent ie) {
		// TODO Auto-generated method stub
		if(ie.getSource()==start){
			item=start.getSelectedItem();
			try{
				connection();
				boolean com=GetFromSql(item);
			}catch(Exception ee){
				ee.printStackTrace();
			}
		}
	}

	private boolean GetFromSql(String item) throws SQLException {
		// TODO Auto-generated method stub
		String sql;
		Connection con=super.con;
		Statement stmt=con.createStatement();
		sql="SELECT number FROM view WHERE name='"+item+"'";
		rs=stmt.executeQuery(sql);
		while(rs.next()){
			num=rs.getInt(1);
			return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(ae.getSource()==confirm){
			try {
				new FindTravel(num,item);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(ae.getSource()==back){
			new MainMenu();
		}
	}

}
