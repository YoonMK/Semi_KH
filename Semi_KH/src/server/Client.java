package server;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import server.model.UserDto;

import MainFrame.IdFindMain;
import MainFrame.PwFindMain;
import MainFrame.JoinMain;


//import server.ClientMain.Receiver;

class IdfindButton implements ActionListener 
{
	int kind = 0;
	public IdfindButton(int num) {
		this.kind = num;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(kind==0)new IdFindMain();
		if(kind==1)new PwFindMain();
		if(kind==2)new JoinMain();
	}
}

public class Client extends JFrame {

	JTextField ip_F = new JTextField();// ip �Է�â
	JTextField port_F = new JTextField();// ��Ʈ�Է�â

	JButton ip_chk = new JButton("����"); // ip Ȯ�ι�ư

	JTextArea txt_area= new JTextArea(); // ä��â
	JScrollPane txt_scrol = new JScrollPane(txt_area);// ä��â��ũ��

	JTextField chat_area= new JTextField();//ä���Է�â
	JButton chat_chk = new JButton("����"); // ���۹�ư

	Socket socket;

	String ip;
	int port;

	UserDto dto = new UserDto();

	JLabel id = new JLabel("I  D:");
	JLabel pw = new JLabel("PW:");
	JTextField idtf = new JTextField();
	JPasswordField pwtf = new JPasswordField();
	JButton chk = new JButton("�α���");
	JButton idfind = new JButton("���̵�ã��");
	JButton pwfind = new JButton("��й�ȣã��");
	JButton join = new JButton("ȸ������");
	public Client() 
	{
		setTitle("�����̻ѳ�");
		setBounds(10,20,920,690);
		setLayout(null);
		id.setBounds(300, 400, 70, 30);
		id.setFont(new Font("Serif", Font.BOLD, 25));
		add(id);
		pw.setBounds(300, 450, 70, 30);
		pw.setFont(new Font("Serif", Font.BOLD, 25));
		add(pw);
		idtf.setBounds(360, 400, 240, 30);
		add(idtf);
		pwtf.setBounds(360, 450, 240, 30);
		add(pwtf);
		chk.setBounds(500, 500, 100, 40);
		add(chk);
		chk.addActionListener(new Chk_Button());
		idfind.setBounds(300, 550, 100, 40);
		add(idfind);
		idfind.addActionListener(new IdfindButton(0));
		pwfind.setBounds(400, 550, 100, 40);
		pwfind.addActionListener(new IdfindButton(1));
		add(pwfind);
		join.setBounds(500, 550, 100, 40);
		join.addActionListener(new IdfindButton(2));
		add(join);

		//id.setText("admin");
		//	pw.setText("admin");


		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	class Chk_Button implements ActionListener {// �α��� ������
		@Override
		public void actionPerformed(ActionEvent e) {

			String id_chk ="admin";
			String pw_chk ="admin";
			/*ip = "127.0.0.1";//"192.168.219.124";
			port = 7777;
			socket(ip,port);*/

			// if(id.equals("admin")){
			if((idtf.getText().equals(id_chk))) {
				if((pwtf.getPassword().equals(pw_chk))) {
					System.out.println("�α��� ����");
				}
			}
			else
				System.out.println("�α��� ����");
			
			
//			else if(pw.equals("admin"))
//			{
//			dto.setPw(pw);
//			System.out.println("�α��� ����");
//			}
//
//			else{
//				System.out.println("�α��� ����");
//
//			}
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

			txt_area.append("���� ���� ����\n");
			new Receiver(socket).start();
		} 

		catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		new Client();
	}

}