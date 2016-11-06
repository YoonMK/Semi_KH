package A0;



import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

class PuyoFrametest extends JFrame 

{
	PuyoPanel myPanel;        // 패널끌고오는거

	public PuyoFrametest()
	{ super("dd?");
	setBounds(20, 20, 920, 690);
	setLayout(null);
	makeGUI();
	setVisible(true);

	}  

	void makeGUI()
	{
		Container c = getContentPane();    

		myPanel = new PuyoPanel();
		myPanel.setBounds(5,20,280,600);

		c.add(myPanel, "Center");


	} 

	class Block{

		int  centerX,centerY;
		int radius;

		Color color;
		int pipe;    

		Block(int centerX, int centerY, int radius, Color color){
			this.centerX= centerX;   
			this.centerY= centerY;
			this.radius = radius;
			this.color  = color;     


		}


		public void paint(Graphics g){
			g.setColor(color);
			g.fillOval(centerX-radius,centerY-radius, 2*radius,2*radius);
		}
	}


	//파이프로 구성
	//벽돌의 이동
	class BlockPipe{


		LinkedList blocks;


		public BlockPipe(){
			blocks= new LinkedList();
		}

		public void paint(Graphics g){

			Iterator itr= blocks.iterator();

			while(itr.hasNext())
			{
				Object element= itr.next();
				Block b = (Block) element;
				b.paint(g);
			}
		}
		/*
		public int getSize()
		{
			return blocks.size();
		}*/
	}


	//블록의 짝 a,b
	class BlockPair{

		Block blockA; 
		Block blockB;
		int blockB_OR;  //A를 기준으로 B가 움직임 용
		int pipe; 



		public void paint(Graphics g){
			blockA.paint(g);
			blockB.paint(g);
		}

	}
	///////////////////////////////////////////////////////////////////////////////////////////
	interface GameParameters{

		int width=250;
		int height=690;
		int numpi = 4;
		Color colors[]={Color.RED,Color.BLUE,Color.GREEN, Color.YELLOW};
		//방향 움직이는 것
		int RIGHT=1,LEFT=2,DOWN=3, UP=4;

		int RIGHT_OR = 3, TOP_OR=2,LEFT_OR=1, BOTTOM_OR=0;
		//B기준  B가오른쪽     B가 위에      B가왼쪽         B가 아래로 
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////

	public class PuyoGame  implements GameParameters {

		BlockPair   currentpair;   // 블록쌍
		BlockPipe   pipes[];        // 
		long   score=0;


		public void StartGame()
		{

			currentpair =new BlockPair();
			pipes =new BlockPipe[numpi];

			for(int i =0;i<numpi;++i)
				pipes[i]=  new BlockPipe();        

			ReleasePair();//첫번째 쌍 생성
		}

		public void ReleasePair()
		{

			Random r= new Random();

			int color_index=r.nextInt(4); 
			Block blockA  = 
					new Block(70, 20 ,20, colors[color_index]); 

			int color_index2=r.nextInt(4); 
			Block blockB = 
					new Block(70,60, 20, colors[color_index2]);


			currentpair.blockA=blockA;
			currentpair.blockB=blockB;
		}    


		public void Render(Graphics g)
		{
			g.setColor(Color.BLACK);

			currentpair.paint(g);

			for(int i =0;i<numpi;++i)
				pipes[i].paint(g);     

		}         


		boolean MoveBlock(Block blk){


			if(!CheckBlock(blk,DOWN)){
				blk.centerY+= 1;
				return true;
			}
			else{
				return false;
			}

		}

		//현재 blockPair 움직
		void MovePair(){


			//블록을 움직인다
			boolean ba = MoveBlock(currentpair.blockA);

			boolean bb = MoveBlock(currentpair.blockB); 
		}

		boolean CheckBlock(Block blk, int dir)
		{
			switch(dir)
			{
			case  LEFT:
				return  
						blk.pipe ==0;
			case RIGHT :
				return blk.pipe==(numpi-1);

			case DOWN :{
				return blk.centerY+20>height;
			}
			default:
				return false;

			}
		}

		public void ProcessKey(KeyEvent e){


			int key= e.getKeyCode();

			switch(key){


			case KeyEvent.VK_LEFT:
				MovePairTo(LEFT);
				break;

			case KeyEvent.VK_RIGHT:
				MovePairTo(RIGHT);
				break;

			case KeyEvent.VK_UP:
				TransPair();

				break;

			case KeyEvent.VK_DOWN:
				MovePairTo(DOWN);
				break;     

			case KeyEvent.VK_SPACE:

				break;     

			}                           


		}

		void TransPair()
		{
			int bmove = currentpair.blockB_OR;
			switch(bmove)
			{


			case  BOTTOM_OR:    
			{

				currentpair.blockB_OR=RIGHT_OR;
				currentpair.blockB.centerY=currentpair.blockA.centerY ;
				currentpair.blockB.centerX=currentpair.blockA.centerX + 40;
				break;
			}

			case RIGHT_OR: 

			{
				currentpair.blockB_OR=TOP_OR;
				currentpair.blockB.centerX=currentpair.blockA.centerX;
				currentpair.blockB.centerY=currentpair.blockA.centerY - 40;
				break;
			}
			case TOP_OR:

			{ 	
				currentpair.blockB.centerY=currentpair.blockA.centerY;
				currentpair.blockB.centerX=currentpair.blockA.centerX -  40;
				currentpair.blockB_OR=LEFT_OR;


				break;
			}
			case LEFT_OR: 
			{
				currentpair.blockB_OR=BOTTOM_OR;
				currentpair.blockB.centerY=currentpair.blockA.centerY +  40;
				currentpair.blockB.centerX=currentpair.blockA.centerX;
				break;
			}

			}
		}

		void MovePairTo(int dir){

			switch(dir)
			{

			case DOWN:
			{
				switch(currentpair.blockB_OR){

				case TOP_OR:
					if(!CheckBlock(currentpair.blockA, DOWN))
					{
						currentpair.blockA.centerY+=40;
						currentpair.blockB.centerY+=40;
					}
					break;
				default:
					if(!CheckBlock(currentpair.blockB, DOWN))
					{
						currentpair.blockA.centerY+=40;
						currentpair.blockB.centerY+=40;


					}
					break;
				}
				break;
			}
			

			case LEFT :
			{
				
				currentpair.blockA.centerX-=40;
				currentpair.blockB.centerX-=40;
				break;
			}	

			case RIGHT : 
			{

				currentpair.blockA.centerX+=40;
				currentpair.blockB.centerX+=40
						;
				break;
			}

			}
			}

		}



		class PuyoPanel extends JPanel implements Runnable, GameParameters
		{
			int PWIDTH = width; 
			int PHEIGHT = height; 


			Thread animator;
			boolean running = false;   


			PuyoFrametest topFrame;
			PuyoGame myPuyo;


			int score = 0;



			Graphics dbg; 
			Image dbImage = null;

			public PuyoPanel()
			{


				setBackground(Color.white);

				setPreferredSize( new Dimension(PWIDTH, PHEIGHT));

				//setBounds(20, 30, 150, 150);
				setFocusable(true);


				myPuyo= new PuyoGame();
				myPuyo.StartGame();

				startGame();

				addKeyListener( new KeyAdapter() {

					public void keyPressed(KeyEvent e)
					{ 
						myKeyPressed(e); }
				});
			}  

			void myKeyPressed(KeyEvent e)
			{ 

				myPuyo.ProcessKey(e);
			}



			void startGame()

			{ 
				if (animator == null || !running) {
					animator = new Thread(this);
					animator.start();
				}
			} 



			public void run()

			{

				Graphics g;


				running = true;

				while(running) {
					gameUpdate(); 
					gameRender();   // 버퍼에 게임.
					paintScreen();  // 화면 버퍼를 그리

					try {
						Thread.sleep(114);

					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}


			void gameUpdate() 
			{ 
				myPuyo.MovePair();

			}  


			void gameRender()
			{
				if (dbImage == null){
					dbImage = createImage(PWIDTH, PHEIGHT);

					return;

				}

				else{
					dbg = dbImage.getGraphics();
				}

				//배경
				dbg.setColor(Color.white);
				dbg.fillRect (0, 0, PWIDTH, PHEIGHT);

				myPuyo.Render(dbg);


			}  
			void paintScreen()

			{ 
				Graphics g;
				try {
					g = this.getGraphics();
					if ((g != null) && (dbImage != null))
						g.drawImage(dbImage, 0, 0, null);

					g.dispose();
				}
				catch (Exception e)
				{ 

				}
			} 
		}
		public 	static void main(String args[])
		{ 

			new PuyoFrametest();    
		}

	}
