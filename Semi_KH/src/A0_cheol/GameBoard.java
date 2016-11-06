package A0_cheol;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameBoard extends JPanel {//블럭만 있는 클레스.

	public Color[] colors={Color.pink,Color.blue,Color.red,Color.yellow};
	
		public int row = 280;//가로의 길이;
		public int col = 600;//세로의 길이
		int x =100, y=100;

		JPanel GameUser = new JPanel();
		JLabel icon = new JLabel();
		
		public GameBoard()// 게임 플레이 장을 만들기 위해서.
		{	
			GameUser.setBounds(5, 20, row, col);
			GameUser.setBackground(Color.white);
			///////////////////////////////
			icon.setBounds(x, y, 100, 100);
			icon.setBackground(colors[0]);
			icon.setOpaque(true);
			add(icon);	
			
		}
		
		class mymy extends KeyAdapter
		{
 
			
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
			}
		}
	
		
		
		
}
