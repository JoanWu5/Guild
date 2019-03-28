package guide;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.awt.*;

public class FindTravel extends JFrame implements ActionListener{
	/**
	 * @author ������
	 * ����·���Ƽ��Ľ����·������Ҿ����ľ�������8��
	 */
	private JButton back=new JButton("�������˵�");
	private JButton exit=new JButton("�˳�");
	private JLabel title;
	private JLabel dis=new JLabel("����Ϊ��");
	private JTextArea text=new JTextArea();
	private JTextField distance=new JTextField();
	private static final int inf=Integer.MAX_VALUE;
	private int x=50,y=50;
	Image pic=Toolkit.getDefaultToolkit().getImage("images\\map.png");;
	public FindTravel(){
		super("����·���Ƽ�");
	}
	
	public FindTravel(int num, String item) throws SQLException {
		// TODO Auto-generated constructor stub
		super("����·���Ƽ�");
		DecimalFormat df=new DecimalFormat("0.00");//��double���͵Ľ������ΪС�������λ
		double shortestDistance=(double)inf,temp=0;;
		double[][] weight=new Weight().Weight();//�����ͼ
		int paths[] = null,finalDestination = 0;
		int viewNumbers=0,finalNumbers = 0;//viewNumbers��ʾ�����ľ�����Ŀ��finalnumbers��ʾ������̾��뾭���ľ�����
		for(int view=1;view<=42;view++){//1~42��ʾ���Ǿ��㣬������start��view�㣬�ȽϾ���
			viewNumbers=0;
			if(view!=num-1){
				paths= new Dijkstra().Dijkstra(weight, num-1, view-1);//����Dijkstra�㷨���õ������ĵ��·��
				for(int i=0;i<paths.length-1;i++){
					temp+=weight[paths[i]][paths[i+1]];	//��start��view�ľ���
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
		paths= new Dijkstra().Dijkstra(weight, num-1, finalDestination-1);//����·��
		String length=df.format(shortestDistance);
		distance.setText(length);
		double[][] xy=new SelectSinglePoint().SelectSinglePoint(paths);//�õ�·����ÿ�����x.y����
		/*for(int i=0;i<paths.length;i++){
			System.out.print(paths[i]+" ");
		}
		System.out.println();*/
		String[] names=new SelectName().SelectName(paths,1);//�õ�ÿ��·���еĵ������
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
		text.append("\n������"+finalNumbers+"������");
		JPanel jp=new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(pic,x,y,1198,885,this);
				Graphics2D g2 = (Graphics2D)g;  
				g2.setStroke(new BasicStroke(3.0f));//�Ӵ���
				g2.setColor(Color.RED);
				for(int i=0;i<xy.length-1;i++){
					g2.drawLine(x+(int)xy[i][0], y+(int)xy[i][1], x+(int)xy[i+1][0], y+(int)xy[i+1][1]);
				}
			}
		};
		jp.setLayout(null);
		title=new JLabel("��"+item+"�������������·����̵���·");
		title.setFont(new Font("����",Font.BOLD,14));
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
