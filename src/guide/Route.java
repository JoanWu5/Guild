package guide;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class Route extends JFrame{
	/**
	 * @author 王超凡
	 * 路线界面，选择起点和终点
	 */
	private int x=50,y=120;
	private Choice from=new Choice();
	private Choice to=new Choice();
	private JButton confirm=new JButton("确定");
	private JButton back=new JButton("返回主菜单");
	private String[] items=new String[42];
	private Route_db rdb;
	Image map=Toolkit.getDefaultToolkit().getImage("images\\map.png");
	
	public void readFile(String file){
		try{
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line=null;
			int i=0;
			while((line=in.readLine())!=null){
				if(line .equals(".QUIT")){
					break;
				}
				items[i]=new String(line);
				i++;
			}
			in.close();
		}catch(IOException ioe){
			System.out.println("error:"+ioe);
		}
	}
	
	public Route(){
		super("路线查询");
		JPanel jp=new JPanel(){
			protected void paintComponent(Graphics g){
				g.drawImage(map,x,y,1198,885,this);
				g.setColor(Color.black);
				g.setFont(new Font("宋体",Font.BOLD,20));
				g.drawString("选择起点终点",200, 60);
			}
		};
		jp.setLayout(null);
		readFile("file\\view.txt");
		rdb=new Route_db();
		rdb.setButtons(confirm, back);
		rdb.setChoices(from, to);
		confirm.setBounds(900, 38, 100, 30);
		confirm.addActionListener(rdb);
		jp.add(confirm);
		back.setBounds(1050, 38, 100, 30);
		back.addActionListener(rdb);
		jp.add(back);
		for(int i=0;i<items.length;i++){
			from.add(items[i]);
		}
		from.setBounds(350, 40, 200, 50);
		from.addItemListener(rdb);
		jp.add(from);
		for(int i=0;i<items.length;i++){
			to.add(items[i]);
		}
		to.setBounds(600, 40, 200, 50);
		to.addItemListener(rdb);
		jp.add(to);
		add(jp);
		setSize(1300,1200);    
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
	}
	
	/*public static void main(String[] args){
		Route ro=new Route();
	}*/
}
