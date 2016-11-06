package A0_cheol;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class game  extends JFrame{
//	JPanel GameUser = new JPanel();
	
	//GameBoard gb = new GameBoard();// 새롭게 new 를 지정 해줘야 사용이 가느아하다.
	JPanel gameboard = new JPanel();
	JPanel user = gb.GameUser;// JP 을 만들고 그걸 gb.GameUser 에서 만든 J페널을 넣는다. ;
//	JLabel icn = gb.icon;
	
//	int x =10, y=10;
	public game() 
	{
		setBounds(20, 20, 920, 690);
		setLayout(null);
		
		add(user);
//		user.add(gb.icon);

		gameboard.setBounds(1,1,1,1);
		gameboard.setBackground(Color.LIGHT_GRAY);
		add(gameboard);
		
		
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new game();
	}
}
