package guide;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener{
	/**
	 * @author 王超凡
	 * 菜单界面，包括景点查询，路线查询，游览路线推荐
	 */
	private JButton viewButton=new JButton("景点查询");
	private JButton routeButton=new JButton("线路查询");
	private JButton travelButton=new JButton("游览路线推荐");
	private JLabel title=new JLabel("菜单选择");
	
	public MainMenu() {
		super("菜单");
		setLayout(null);
		title.setFont(new Font("宋体",Font.BOLD,14));
		title.setBounds(150, 30, 100, 20);
		viewButton.setBounds(100, 70, 150, 20);
		routeButton.setBounds(100, 120, 150, 20);
		travelButton.setBounds(100, 170, 150, 20);
		add(title);
		add(viewButton);
		add(routeButton);
		add(travelButton);
		viewButton.addActionListener(this);
		routeButton.addActionListener(this);
		travelButton.addActionListener(this);
		pack();
		setSize(370,300);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==viewButton) {
			new View();
		}else if(e.getSource()==routeButton) {
			new Route();
		}else if(e.getSource()==travelButton) {
			new Travel();
		}
	}
	
	/*public static void main(String[] args) {
		MainMenu main=new MainMenu();
	}*/
}
