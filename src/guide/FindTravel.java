package guide;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.awt.*;

public class FindTravel extends JFrame implements ActionListener{
	/**
	 * @author 王超凡
	 * 旅游路线推荐的结果，路线最短且经过的景点至少8个
	 */
	private JButton back=new JButton("返回主菜单");
	private JButton exit=new JButton("退出");
	private JLabel title;
	private JLabel dis=new JLabel("距离为：");
	private JTextArea text=new JTextArea();
	private JTextField distance=new JTextField();
	private static final int inf=Integer.MAX_VALUE;
	private int x=50,y=50;
	Image pic=Toolkit.getDefaultToolkit().getImage("images\\map.png");;
	public FindTravel(){
		super("游览路线推荐");
	}
	
	public FindTravel(int num, String item) throws SQLException {
		// TODO Auto-generated constructor stub
		super("旅游路线推荐");
		DecimalFormat df=new DecimalFormat("0.00");//将double类型的结果保存为小数点后两位
		double shortestDistance=(double)inf,temp=0;;
		double[][] weight=new Weight().Weight();//导入地图
		int paths[] = null,finalDestination = 0;
		int viewNumbers=0,finalNumbers = 0;//viewNumbers表示经过的景点数目，finalnumbers表示最终最短距离经过的景点数
		for(int view=1;view<=42;view++){//1~42表示的是景点，遍历从start到view点，比较距离
			viewNumbers=0;
			if(view!=num-1){
				paths= new Dijkstra().Dijkstra(weight, num-1, view-1);//调用Dijkstra算法，得到经过的点的路径
				for(int i=0;i<paths.length-1;i++){
					temp+=weight[paths[i]][paths[i+1]];	//从start到view的距离
				}
				for(int i=0;i<paths.length;i++){
					if(paths[i]<=42&&paths[i]!=num-1)
						viewNumbers++;
				}
				if(viewNumbers>=8&&shortestDistance>temp){
					shortestDistance=temp;
					finalDestination=view;
					finalNumbers=viewNumbers;
				}
				/*for(int i=0;i<paths.length;i++){
					System.out.print(paths[i]+" ");
				}
				System.out.println();*/
			}
		}
		//System.out.println(finalNumbers);
		paths= new Dijkstra().Dijkstra(weight, num-1, finalDestination-1);//最终路径
		String length=df.format(shortestDistance);
		distance.setText(length);
		double[][] xy=new SelectSinglePoint().SelectSinglePoint(paths);//得到路径中每个点的x.y坐标
		/*for(int i=0;i<paths.length;i++){
			System.out.print(paths[i]+" ");
		}
		System.out.println();*/
		String[] names=new SelectName().SelectName(paths,1);//得到每个路径中的点的名字
		/*for(int i=0;i<names.length;i++)
			System.out.print(names[i]+" ");*/
		int i;
		for(i=0;i<names.length-1;i++){
			if(i==0) 
				text.append(names[i]);
			else if(names[i]!=null)
				text.append("-->"+names[i]);
		}
		if(names[i]!=null)
			text.append(names[i]);
		text.append("\n经历了"+finalNumbers+"个景点");
		JPanel jp=new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(pic,x,y,1198,885,this);
				Graphics2D g2 = (Graphics2D)g;  
				g2.setStroke(new BasicStroke(3.0f));//加粗线
				g2.setColor(Color.RED);
				for(int i=0;i<xy.length-1;i++){
					g2.drawLine(x+(int)xy[i][0], y+(int)xy[i][1], x+(int)xy[i+1][0], y+(int)xy[i+1][1]);
				}
			}
		};
		jp.setLayout(null);
		title=new JLabel("从"+item+"游览景点最多且路线最短的线路");
		title.setFont(new Font("宋体",Font.BOLD,14));
		title.setBounds(1300, 50, 450, 50);
		jp.add(title);
		text.setBounds(1280, 100, 350, 300);
		text.setLineWrap(true);
		jp.add(text);
		dis.setBounds(1300, 500, 80, 30);
		jp.add(dis);
		distance.setBounds(1400, 500,200, 30);
		jp.add(distance);
		back.setBounds(1320,600,100,30);
		back.addActionListener(this);
		jp.add(back);
		exit.setBounds(1470,600,100,30);
		exit.addActionListener(this);
		jp.add(exit);
		add(jp);
		jp.setOpaque(false);
		setSize(1700,1000);    
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
