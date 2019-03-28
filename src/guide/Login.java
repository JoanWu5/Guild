package guide;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame{
	/**
	 * @author ������
	 * ��¼����
	 */
	private static final long serialVersionUID = 1L;
	private JLabel mainlabel=new JLabel("�û���¼");
	private JLabel[] label={new JLabel("ID"),new JLabel("Password")};
	private JTextField idtext=new JTextField(20);
	private JPasswordField pwtext=new JPasswordField(20);
	private JButton loginButton=new JButton("��¼");
	private JButton registerButton=new JButton("ע��");
	private Login_db log;
	
	public Login(){
		super("�û���¼");
		log=new Login_db();
		mainlabel.setFont(new Font("����",Font.BOLD,14));
		loginButton.addActionListener(log);
		registerButton.addActionListener(log);
		setLayout(null);
		mainlabel.setBounds(180,50,80,26);
		add(mainlabel);
		for(int i=0;i<2;i++){
			label[i].setBounds(50,120+i*50,80,30);
			add(label[i]);
		}
		idtext.setBounds(150, 120, 150, 30);
		pwtext.setBounds(150, 170, 150, 30);
		add(idtext);
		add(pwtext);
		loginButton.setBounds(100, 300, 80, 20);
		registerButton.setBounds(200, 300, 80, 20);
		add(loginButton);
		add(registerButton);
		log.setButtons(loginButton, registerButton);
		log.setname(idtext);
		log.setPassword(pwtext);
		pack();
		setSize(400,450);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		
	}
	
	public static void main(String[] args){
		Login lo=new Login();

	}
}
