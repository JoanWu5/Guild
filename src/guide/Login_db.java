package guide;
import java.awt.Window;
import java.awt.event.*;
import javax.swing.*;

import guide.MainMenu;

import java.sql.*;

public class Login_db extends conn_db implements ActionListener{
	/**
	 * @author 王超凡
	 * 登录连接数据库，如果账户和密码为空，弹出对话框，并要求重新输入
	 */
	private JTextField idT;
	private JPasswordField pwT;
	private JButton loginButton,registerButton;
	private Register re;
	private ResultSet rs;
	
	public void setname(JTextField a){
		idT=a;
	}
	
	public void setPassword(JPasswordField b){
		pwT=b;
	}
	
	public void setButtons(JButton b1,JButton b2){
		loginButton=b1;
		registerButton=b2;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==loginButton){
			if(idT.getText()=="")
				JOptionPane.showMessageDialog(null, "请填写账号！"); 
			else {
				String pa=String.valueOf(pwT.getPassword());
				if(pa.length()==0)
					JOptionPane.showMessageDialog(null, "请填写密码！"); 
				else{
					String idString=idT.getText();
					try{
						connection();
						boolean com=CompareWithSql(idString,pa);
						if(com){
							JOptionPane.showMessageDialog(null, "登录成功");  
							new MainMenu();
						}else{
							JOptionPane.showMessageDialog(null, "账号或密码为空，请重新输入");  
							idT.setText("");
							pwT.setText("");
						}
					}catch(Exception ee){
						ee.printStackTrace();
					}
				}
			}
		}else if(e.getSource()==registerButton){
			new JFrame().dispose();
			re=new Register();
		}
			
	}

	private boolean CompareWithSql(String idString, String pwString) throws Exception{
		// TODO Auto-generated method stub
		String sql;
		Connection con=super.con;
		Statement stmt=con.createStatement();
		sql="SELECT * FROM user";
		rs=stmt.executeQuery(sql);
		while(rs.next()){
			String id=rs.getString(1);
			String ps=rs.getString(2);
			if(id .equals(idString)&&ps.equals(pwString)){
				return true;
			}
		}
		return false;
	}
	
}
