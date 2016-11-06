package A0_cheol;

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






public class PuyoFrametest extends JFrame // 전체 패널 입니다

{
   PuyoPanel myPanel;        // 패널끌고오는거
   
   public PuyoFrametest()//생성자 입니다.JF의
   { super("dd?");//이름
   setBounds(20, 20, 920, 690);//크기 좌표
   setLayout(null);
   makeGUI();
   setVisible(true);
   }  


   void makeGUI()// 이 메소드는 내가 플레이한 JP널을 사용 하기 이해서 만들었습니다
   {
      Container c = getContentPane();  // 컨테이너 =>는 도구  세영이가 만든 페널을  프레임 안에 넣기 위해 사용한.

      myPanel = new PuyoPanel();// JP을 시용 하기 위해서 new 로 선언 했다.
      myPanel.setBounds(5,20,280,600);// 플레이창의 페널의 크기 및 좌표
      
      c.add(myPanel, "Center");
      //컨ㅌㅔ이너를 사용해서 페널을 붙이고 그걸 가운데에 넣겟다 고 선언.


   }
   ///////////////////////////////////////////////////////////////
   //또다른 인어 클레스.
   class Block//블럭 클레스를 만들어주고.
   {

      int  centerX,centerY;// 블럭의 x,y이 의 좌표를 설정 하기위해서.
      int radius;//보류!>_<
      Color color;    //칼라.
   
      Block(int centerX, int centerY, int radius, Color color)
      {// 생성자를 만들고 그 생성자안에선 4가지의 데이터를 넣게 한다.
         this.centerX= centerX;   
         this.centerY= centerY;
         this.radius = radius;
         this.color  = color;     


      }


      public void paint(Graphics g)//페인트 메소드 만들고 그레픽을 한다.
      {
         g.setColor(color);//원의 색은 여것.
         g.fillOval(centerX-radius,centerY-radius, 2*radius,2*radius);
         //원을 그리는 데 그리는  그것에 위치를 설정.
      }
   }

/////////////////////////////////////////////////////////////////////////////////////
   //파이프로 구성
   //벽돌의 이동
   class BlockPipe{//


      LinkedList blocks;// 링크드 리스트를 만들고
      public void paint(Graphics g){
         blocks= new LinkedList();// 생성자를 만들고 블럭을을 세로베 정의를 해준다.
         Iterator itr= blocks.iterator();

         while(itr.hasNext()) // d와일 문을돌리면서 실행 시켜준다.
         {
   
            Object element= itr.next();// 오버젝트 형식 만들고. itr.next 형식으로 가지고온다.
            Block b = (Block) element;//블럭형 b 에  블럭형식으로 ㅂ형변환 시켜준상태로 element로 바꿔서 넣어준다.
            b.paint(g);//  그 b를  그래픽으로 뽑아준ㄷ
   
         }
      }

//      public int getSize()
//      {
//         return blocks.size();
//      }
   }
   
   //블록의 짝 a,b
   class BlockPair{// 블럭을 클렛스를 생성 

      Block blockA;  // 블럭 크기 및 생성위치를 만들기 위해 1번 블럭
      Block blockB;// 위와 같이 2번 블럭
   
      
      public void paint(Graphics g)
      {
         blockA.paint(g);// 블럭에 있는 값들을 쓰기위해
         blockB.paint(g);// 블럭에 있는 값들을 쓰기위해
      }

   }

   interface GameParameters{
 
      int width=252;// 게임 플레이 JP 페널의  크기
      int height=width*2+0;//위에크기 * 2 한것.
      int numpi = 4;
      Color colors[]={Color.RED,Color.BLUE,Color.GREEN, Color.YELLOW};// 각 객체의  색갈들.
      //방향 움직이는 것
      int RIGHT=1,LEFT=2,DOWN=3, UP=4;
      //방향의 숫자들.
      
   }

   public class PuyoGame  implements GameParameters {
	   // 게임을  인터페이스 에서 받은 정보들로 사용 하기 위해서,
      BlockPair   currentpair;   // 블록1,2, 번으로 나온 것을 가지고 쓰기 위해서.
      BlockPipe   pipes[];       // // 위에서 만든 블럭 파이프를 배열로 만듬.
      long   score=0;// 롱 형태로 받고 score를 초기값 0을 준다.


      public void StartGame()//게임을 스타트
      {

         currentpair =new BlockPair();//1,2 블럭을 새롭게 정의를 해서 currentpair   지정해준다.
         pipes =new BlockPipe[numpi];//블럭 파이프배열을 사용하고 그안에 numpi(4)개의 방을 만들었다.
         //그걸 pipes 사용 할것이다.

         for(int i =0;i<numpi;++i)// 4번을 돌리고 
            pipes[i]=  new BlockPipe();//        

         ReleasePair();//메소드로 블럭을 생성.

      }


      public void ReleasePair()
      {

         Random r= new Random();//렌덤으로 만들고 r을

         int color_index=r.nextInt(4); //위에서 만든 4가지의 색 배열을  렌덤으로 주고
         // 그걸 int 형테로 바꿔서 준다.
         Block blockA  = // A블럭의 크기 및 색
               new Block(70, 20 ,23, colors[color_index]); 

         int color_index2=r.nextInt(4); //2번째 블럭의 색

         Block blockB = //두번쨰 블럭의 크기 및ㅍ 색.
               new Block(70,65, 23, colors[color_index2]);


         currentpair.blockA =blockA;//위에서 만들 블럭들을.
         currentpair.blockB=blockB;
      }    
//      A0.PuyoFrametest$Block@7530d0a???
//      A0.PuyoFrametest$Block@27bc2616???

      public void Render(Graphics g)
      {
         g.setColor(Color.BLACK);

         currentpair.paint(g);

         for(int i =0;i<numpi;++i)
            pipes[i].paint(g);     

      }         


      boolean MoveBlock(Block blk){


         blk.centerY+=1;

         return false;

      }

      //현재 blockPair 움직
      void MovePair(){


         //블록을 움직인다
         boolean ba = MoveBlock(currentpair.blockA);
         boolean bb = MoveBlock(currentpair.blockB); 
         
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
            
            break;

         case KeyEvent.VK_DOWN:
            MovePairTo(DOWN);
            break;     

         case KeyEvent.VK_SPACE:
            break;     

         }                           


      }



   void MovePairTo(int dir){

         switch(dir)
         {

         case DOWN:
         {

            currentpair.blockA.centerY+=4;
            currentpair.blockB.centerY+=4;
            break;
         }

         case LEFT :
         {

            currentpair.blockA.centerX-=4;
            currentpair.blockB.centerX-=4;
            break;
         }   

         case RIGHT : 
         {

            currentpair.blockA.centerX+=4;
            currentpair.blockB.centerX+=4;
            break;
         }
         }
      }

   }
   
   
////////////////////////////////////////////////////////////////////////////
   class PuyoPanel extends JPanel implements Runnable, GameParameters
   {// 게임을 플레 창이 있는 JP널!
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
               Thread.sleep(14);

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
   public    static void main(String args[])
   { 

      new PuyoFrametest();    
   }

}