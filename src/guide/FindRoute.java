package guide;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.awt.*;

public class FindRoute extends JFrame implements ActionListener{
	/**
	 * @author 王超凡
	 * 路线查询的结果,得到从起点到终点路线最短的路径
	 */
	private JButton back=new JButton("返回主菜单");
	private JButton exit=new JButton("退出");
	private JLabel title;
	private JTextField text=new JTextField();
	private int x=50,y=100;
	Image pic=Toolkit.getDefaultToolkit().getImage("images\\map.png");;
	public FindRoute(){
		super("路线查询");
	}
	
	public FindRoute(int number1, int number2, String item1, String item2) throws SQLException {
		// TODO Auto-generated constructor stub
		super("路线查询");
		DecimalFormat df=new DecimalFormat("0.00");
		double shortestDistance=0;
		double[][] weight=new Weight().Weight();
		int paths[];
		paths= new Dijkstra().Dijkstra(weight, number1-1, number2-1);
		/*for(int i=0;i<paths.length;i++){
			System.out.println(paths[i]);
		}*/
		for(int i=0;i<paths.length-1;i++){
			shortestDistance+=weight[paths[i]][paths[i+1]];
		}
		String length=df.format(shortestDistance);
		text.setText(length);
		double[][] xy=new SelectSinglePoint().SelectSinglePoint(paths);
		JPanel jp=new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D)g;  
				g2.setStroke(new BasicStroke(3.0f));
				g.drawImage(pic,x,y,1198,885,this);
				g2.setColor(Color.RED);
				for(int i=0;i<xy.length-1;i++){
					g2.drawLine(x+(int)xy[i][0], y+(int)xy[i][1], x+(int)xy[i+1][0], y+(int)xy[i+1][1]);
				}
			}
		};
		jp.setLayout(null);
		title=new JLabel(item1+"到"+item2+"最短距离为:");
		title.setFont(new Font("宋体",Font.BOLD,14));
		title.setBounds(250, 40, 350, 50);
		jp.add(title);
		text.setBounds(600, 50, 150, 30);
		jp.add(text);
		back.setBounds(800,50,100,30);
		back.addActionListener(this);
		jp.add(back);
		exit.setBounds(950,50,100,30);
		exit.addActionListener(this);
		jp.add(exit);
		add(jp);
		jp.setOpaque(false);
		setSize(1300,1000);    
	    setVisible(true);
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==back){
			new JFrame().dispose();
			new MainMenu();
		}else if(e.getSource()==exit){
			System.exit(0);
		}
	}

}
