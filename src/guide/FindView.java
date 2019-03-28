package guide;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;
import java.text.*;


public class FindView extends JFrame implements ActionListener{
	/**
	 * @author ������
	 * �����ѯ�Ľ�����õ��þ���ĵ����ھ�������ֺ;���ı��͸þ���Ľ���
	 */
	private JButton back=new JButton("�������˵�");
	private JButton exit=new JButton("�˳�");
	private JLabel title;
	private JLabel intro=new JLabel("������Ϣ");
	private JLabel near=new JLabel("���ھ���");
	private JTextArea text;
	private JTextArea list=new JTextArea();
	private int x=50,y=100;
	Image pic;
	
	public FindView(){
		super("�����ѯ");
	}
	
	public FindView(int number, String name, String description) throws SQLException {
		// TODO Auto-generated constructor stub
		super("�����ѯ");
		final int inf=Integer.MAX_VALUE;
		DecimalFormat df=new DecimalFormat("0.00");
		int[] numbers=new int[57];
		double[] distance=new double[57];
		int dis=0;
		number=number-1;
		for(int i=0;i<numbers.length;i++){
			numbers[i]=-1;
		}
		double[][] weight=new Weight().Weight(); 
    	   for(int j=0;j<weight[number].length;j++){
    		   if(weight[number][j]!=0&&weight[number][j]!=inf){
    			String length=df.format(weight[number][j]);
    			distance[dis]=Double.parseDouble(length);
    			numbers[dis]=j+1;
    			dis++;
    		   }
    	   }
    	if(dis==0){
    		list.setText("û�����ھ���");
    	}else{
    		String[] names=new SelectName().SelectName(numbers,0);//�������ھ��������
    		for(int i=0;i<dis;i++){
    			if(names[i]!=null){
    				list.append(names[i]+" ���� "+distance[i]+"\n");
    			}
    		}
    	}
		JPanel jp=new JPanel(){
			protected void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawImage(pic,x,y,600,450,this);
				g.setColor(Color.black);
				g.setFont(new Font("����",Font.BOLD,20));
				g.drawString("�����ѯ",450, 60);
			}
		};
		jp.setLayout(null);
		title=new JLabel(name);
		title.setFont(new Font("����",Font.BOLD,14));
		title.setBounds(730, 60, 200, 50);
		intro.setBounds(770, 100, 200,50);
		jp.add(intro);
		near.setBounds(770, 300, 200, 50);
		jp.add(near);
		jp.add(title);
		number=number+1;
		pic=Toolkit.getDefaultToolkit().getImage("images\\"+number+".jpg");
		text=new JTextArea(description);
		text.setLineWrap(true);
		text.setBounds(700,150,200,150);
		list.setBounds(700,350, 200, 100);
		list.setLineWrap(true);
		jp.add(list);
		jp.add(text);
		back.setBounds(680,500,100,30);
		back.addActionListener(this);
		jp.add(back);
		exit.setBounds(830,500,100,30);
		exit.addActionListener(this);
		jp.add(exit);
		add(jp);
		jp.setOpaque(false);
		setSize(1000,700);    
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
