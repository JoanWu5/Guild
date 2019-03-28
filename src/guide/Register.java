package guide;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Register extends JFrame{
	/**
	 * @author 王超凡
	 * 注册界面，包括用户名，密码，电话，邮箱
	 */
	private static final long serialVersionUID = 1L;
	private JLabel mainlabel=new JLabel("新用户注册");
	private JLabel[] label={new JLabel("ID"),new JLabel("Password"),new JLabel("Phone"),new JLabel("E-mail")};
	private JTextField idtext=new JTextField(20);
	private JPasswordField pwtext=new JPasswordField(20);
	private JTextField phtext=new JTextField(20);
	private JTextField emtext=new JTextField(20);
	private JButton registerButton=new JButton("注册");
	private Register_db regist;
	
	public Register(){
		super("新用户注册");
		mainlabel.setFont(new Font("宋体",Font.BOLD,14));
		regist=new Register_db();
		registerButton.addActionListener(regist);
		setLayout(null);
		mainlabel.setBounds(150,50,80,26);
		add(mainlabel);
		for(int i=0;i<4;i++){
			label[i].setBounds(50,100+i*50,80,30);
			add(label[i]);
		}
		idtext.setBounds(150, 100, 150, 30);
		pwtext.setBounds(150, 150, 150, 30);
		phtext.setBounds(150, 200, 150, 30);
		emtext.setBounds(150, 250, 150, 30);
		add(idtext);
		add(pwtext);
		add(phtext);
		add(emtext);
		registerButton.setBounds(150, 320, 80, 20);
		add(registerButton);
		regist.setButtons(registerButton);
		regist.setname(idtext);
		regist.setEmail(emtext);
		regist.setPassword(pwtext);
		regist.setPhone(phtext);
		pack();
		setSize(400,450);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/*public static void main(String[] args){
		Register re=new Register();
	}*/
}
