package server;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientMain extends JFrame{
	
	JTextField ip_F = new JTextField();// ip 입력창
	JTextField port_F = new JTextField();// 포트입력창
	
	JButton ip_chk = new JButton("접속"); // ip 확인버튼
	
	JTextArea txt_area= new JTextArea(); // 채팅창
	JScrollPane txt_scrol = new JScrollPane(txt_area);// 채팅창스크롤
	
	JTextField chat_area= new JTextField();//채팅입력창
	JButton chat_chk = new JButton("전송"); // 전송버튼
	
	Socket socket;
	
	String ip;
	int port;
	
	public ClientMain() {
		
		setBounds(0,0,600,540);
		setLayout(null);
		
		JPanel text_pan = new JPanel();
		text_pan.setLayout(null);
		text_pan.setBounds(0,0,600,50);
		text_pan.setBackground(Color.gray);
		
		ip_F.setBounds(30,10,300,30);
		port_F.setBounds(390,10,100,30);
		ip_chk.setBounds(500,10,60,40); // ip 확인

		text_pan.add(ip_chk);
		
		ip_chk.addActionListener(new Button());
		add(text_pan);
		
		JPanel out_pan = new JPanel();
		out_pan.setLayout(null);
		out_pan.setBounds(0, 50, 600, 370);
		out_pan.setBackground(Color.gray);
		
		
		txt_scrol.setBounds(15,15,550,350);
		txt_scrol.setBackground(Color.lightGray);
		out_pan.add(txt_scrol);
		add(out_pan);
		
		JPanel chat_pan = new JPanel();
		chat_pan.setLayout(null);
		chat_pan.setBounds(0, 420, 600, 80);
		chat_pan.setBackground(Color.gray);
		
		chat_area.setBounds(10,10,400,50);
		chat_area.setBackground(Color.white);
		chat_pan.add(chat_area);
		chat_area.addKeyListener(new My_key());
		
		chat_chk.setBounds(430,10,60,50); //채팅전송
		chat_pan.add(chat_chk);
		chat_chk.addActionListener(new Chat_chk());
		add(chat_pan);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	class Button implements ActionListener {// ip확인 누를때
		@Override
		public void actionPerformed(ActionEvent e) {
			ip = "127.0.0.1";
			port =7777;
			socket(ip,port);
		}
	}
	
	class Chat_chk implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new TCPSender(socket, chat_area.getText());
			chat_area.setText("");
		}
	}

	class My_key extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
				case KeyEvent.VK_ENTER :
					new TCPSender(socket, chat_area.getText());
					chat_area.setText("");
				break;
			}
		}
	}

	class TCPSender {
		DataOutputStream output;
		String name;
		String chat;
		public TCPSender(Socket socket, String str) {
			this.chat = str;
			try {
				output = new DataOutputStream(socket.getOutputStream());
				name = "["+socket.getLocalAddress()+"]";
				output.writeUTF(name+chat);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	class Receiver extends Thread
	{
		DataInputStream input;
		public Receiver(Socket socket) {
			try {
				input = new DataInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(input!=null)
			{
				try {
					txt_area.append(input.readUTF()+"\n");
					txt_area.setCaretPosition(txt_area.getDocument().getLength());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	void socket(String ip, int port) {
		try {
			Socket socket = new Socket(ip,port);
			this.socket = socket;
			
			txt_area.append("서버 연결 성공\n");
			new Receiver(socket).start();
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ClientMain();
	}
}
