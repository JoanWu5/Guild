package guide;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Register_db extends conn_db implements ActionListener{
	/**
	 * @author Õı≥¨∑≤
	 * ◊¢≤·¡¨Ω” ˝æ›ø‚
	 */
	private JTextField idT,phT,emT;
	private JPasswordField pwT;
	private JButton registerButton;
	private Login lo;
	
	public void setname(JTextField a){
		idT=a;
	}
	
	public void setPhone(JTextField b){
		phT=b;
	}
	
	public void setEmail(JTextField c){
		emT=c;
	}
	
	public void setPassword(JPasswordField d){
		pwT=d;
	}

	public void setButtons(JButton b1){
		registerButton=b1;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==registerButton){
			if(idT.getText()=="")
				JOptionPane.showMessageDialog(null, "«ÎÃÓ–¥’À∫≈£°"); 
			else {
				String pa=String.valueOf(pwT.getPassword());
				if(pa.length()==0)
					JOptionPane.showMessageDialog(null, "«ÎÃÓ–¥√‹¬Î£°"); 
				else if(phT.getText()=="")
					JOptionPane.showMessageDialog(null, "«ÎÃÓ–¥µÁª∞∫≈¬Î£°"); 
				else if(emT.getText()=="")
					JOptionPane.showMessageDialog(null, "«ÎÃÓ–¥” œ‰£°");
				else{
					String idString=idT.getText();
					String phString=phT.getText();
					String emString=emT.getText();
					try{
						connection();
						WriteInSql(idString,pa,phString,emString);
					}catch(Exception ee){
						System.out.println("≤Â»Î ß∞‹");  
						ee.printStackTrace();
					}
				}
			
			}
		}
}

	private void WriteInSql(String idString, String pwString, String phString, String emString) throws Exception{
		// TODO Auto-generated method stub
		String sql;
		Connection con=super.con;
		Statement stmt=con.createStatement();
		sql="INSERT INTO user(id,password,phone,email) values('"+idString+"','"+pwString+"','"+phString+"','"+emString+"')";
		int rw=stmt.executeUpdate(sql);
		if(rw<=0){
			JOptionPane.showMessageDialog(null,"◊¢≤· ß∞‹"); 
		}else{
			JOptionPane.showMessageDialog(null,"◊¢≤·≥…π¶"); 
			lo=new Login();
		}
	}
	
}
