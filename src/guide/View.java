package guide;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class View extends JFrame{
	/**
	 * @author 王超凡
	 * 景点查询界面
	 */
	private int x=50,y=120;
	private Choice box=new Choice();
	private JButton confirm=new JButton("确定");
	private JButton back=new JButton("返回主菜单");
	private String[] items=new String[42];
	private View_db vdb;
	
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

	Image map=Toolkit.getDefaultToolkit().getImage("images\\map.png");
	
	public View(){
		super("景点查询");
		JPanel jp=new JPanel(){
			protected void paintComponent(Graphics g){
				g.drawImage(map,x,y,1198,885,this);
				g.setColor(Color.black);
				g.setFont(new Font("宋体",Font.BOLD,20));
				g.drawString("选择景点",250, 60);
			}
		};
		jp.setLayout(null);
		vdb=new View_db();
		readFile("file\\view.txt");
		vdb.setButtons(confirm, back);
		vdb.setChoice(box);
		confirm.setBounds(750, 38, 100, 30);
		confirm.addActionListener(vdb);
		jp.add(confirm);
		back.setBounds(900, 38, 100, 30);
		back.addActionListener(vdb);
		jp.add(back);
		for(int i=0;i<items.length;i++){
			box.add(items[i]);
		}
		box.setBounds(450, 40, 200, 50);
		box.addItemListener(vdb);
		jp.add(box);
		add(jp);
		jp.setOpaque(false);
		setSize(1300,1200);    
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
	}
	
	/*public static void main(String[] args){
		View vi=new View();
	}*/
}
