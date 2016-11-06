package A0_cheol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import A0.PuyoFrametest.Block;
import Sintris.Board;




public class Buyouu2 extends JFrame {

	JPanel GameUser = new JPanel();

	
	Point random;
	Point random2;
	int low =240;
//	int a=-100;
	ImageIcon [] img={
			new ImageIcon("img/red.JPG"),
			new ImageIcon("img/blue.JPG"),
			new ImageIcon("img/green.JPG"),
			new ImageIcon("img/yellow.JPG")
			
			};
	boolean chk =false;
	
	ArrayList<iconn_1>icrr_1 = new ArrayList<>();
	ArrayList<iconn_2>icrr_2 = new ArrayList<>();
	int  dx=20 , dy=5,dy1=5;
	int sp=40;
	class iconn_1 extends JLabel// 기준점,
	{
		Buyouu2 main;
		//ImageIcon chk1 = ;
		public iconn_1(Buyouu2 main) 
		{
			//random=new Point(Board.col/2,0);
			super(img[(int) (Math.random()*4)]);
			random=new Point(low/2,10);// 나오는 좌표를 지정 해주는것.
			//픽맨이 나오는 좌표.

			setBounds(random.x, random.y, 40, 40);
			main.add(this);
		}

		/////게임창 유저1
		void move()
		{// 크기 중간 20 //  밑에서 부터 희선까지  대략 30 // 대략 위에 40.
			random.y+=dy;//움직이게 해준다.
		
		//if(random.x>0 || random.x <300)
		//{
			setLocation(random.x,random.y);
			System.out.println(random.x+"하나번쨰");
			System.out.println(random.y+"하나번쨰");
		//}
			//System.out.println(random.y+"첫번쨰");
			//setLocation(random.x,random.y);

		}

	}
	///////////////////////////////////////
	class iconn_2 extends JLabel//두번째 아이콘.
	{
		Buyouu2 main;
	
		public iconn_2(Buyouu2 main)
		{
			super(img[(int) (Math.random()*4)]);
			random2=new Point(low/2,10);
		
			setBounds(random2.x, random2.y, 40, 40);
			main.add(this);
		}
		
		void move2()
		{// 크기 중간 20 //  밑에서 부터 희선까지  대략 30 // 대략 위에 40.
		
			random2.y+=dy1;//움직이게 해준다.

//			setLocation(random2.x-sp,random2.y);//왼쪽
//			setLocation(random2.x-sp+40,random2.y-40);//위로
//			setLocation(random2.x-sp+80,random2.y);// 오른쪽으로
//			setLocation(random2.x-sp+40,random2.y+40);//밑으로
			System.out.println(random2.x+"두번쨰");

	
		}
		
	}
//////////////////////////////////////////////
	class	iconn_1MO extends KeyAdapter//움직이는 클레스.
	{// 기준점 이 움직일떄 사용되는 키어뎁트.
		@Override
		public void keyPressed(KeyEvent e)// 움직일때 기준점
		{
			System.out.println("키눌럿음");
			
			switch (e.getKeyCode()) // 키누름
			{
				case KeyEvent.VK_LEFT:random.x-=sp;
					break;
				case KeyEvent.VK_RIGHT:random.x+=sp;		
					break;
//				case KeyEvent.VK_DOWN:y+=sp;break;
//				case KeyEvent.VK_UP:y-=sp;break;

			}
		
		}
		
		
	}
/////////////////////////////////////////////	
	
	class Timer extends Thread
	{
	
		int a;
		public void run() {
			// TODO Auto-generated method stub
			ww:while(true)
			{
				try {
					sleep(100);
					for (iconn_1 icon : icrr_1) {
						icon.move();//두번쨰 이미지.
						
					}
					for (iconn_2 icon_2 : icrr_2) {
						icon_2.move2();//쩟번째
						
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	
	
	public Buyouu2()//전체 크기의 패널.
	{
		super("♥세영이뿌네♥");
		setBounds(20, 20, 920, 690);
		setLayout(null);

		/////게임창 유저1
		GameUser.setBounds(5,20,low,600);
		GameUser.setBackground(Color.white);

		////////////////////////////////////
		
		
		icrr_1.add(new iconn_1(this));//첫번째 이미지
		icrr_2.add(new iconn_2(this));//두번쨰 이미지.
		////////////////////////////////////
		add(GameUser);
	
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new iconn_1MO());//키보드를 활용하기 위해서
		new Timer().start();
	}
	////////////////////////////////////////////////////

	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Buyouu2(); 
//////////////////////////////////////
	}
}