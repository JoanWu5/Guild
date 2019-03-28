package guide;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class Travel extends JFrame{
	/**
	 * @author 王超凡
	 * 游览路线推荐界面，选择起点
	 */
	private int x=50,y=120;
	private Choice start=new Choice();
	private JButton confirm=new JButton("确定");
	private JButton back=new JButton("返回主菜单");
	private String[] items=new String[42];
	private Travel_db tdb;
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
	
	public Travel(){
		super("游览路线推荐");
		JPanel jp=new JPanel(){
			protected void paintComponent(Graphics g){
				g.drawImage(map,x,y,1198,885,this);
				g.setColor(Color.black);
				g.setFont(new Font("宋体",Font.BOLD,20));
				g.drawString("选择起点",300, 60);
			}
		};
		jp.setLayout(null);
		readFile("file\\view.txt");
		tdb=new Travel_db();
		tdb.setButtons(confirm, back);
		tdb.setChoice(start);
		
		confirm.setBounds(750, 38, 100, 30);
		confirm.addActionListener(tdb);
		jp.add(confirm);
		back.setBounds(900, 38, 100, 30);
		back.addActionListener(tdb);
		jp.add(back);
		for(int i=0;i<items.length;i++){
			start.add(items[i]);
		}
		start.setBounds(450, 40, 200, 50);
		start.addItemListener(tdb);
		jp.add(start);
		add(jp);
		setSize(1300,1200);    
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
	}


	
	/*public static void main(String[] args){
		Travel tr=new Travel();
	}*/
}
