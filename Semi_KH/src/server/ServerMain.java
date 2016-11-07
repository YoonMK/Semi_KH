package server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class ServerMain {
	HashMap<String, DataOutputStream>clients ;
	
	public ServerMain() {
		
		clients =new HashMap();
		Collections.synchronizedMap(clients);
		SimpleDateFormat sf = new SimpleDateFormat();
		
		try {
			ServerSocket server = new ServerSocket(7777);
			System.out.println("["+sf.format(new Date())+"]"+"���� ����");
			
			while(true)
			{
				Socket client = server.accept();
				System.out.println("["+sf.format(new Date())+"]"+
						"["+"�α���"+":"+client.getPort()+"] ���� ����");
				
				new TCPMulServerReceiver(client).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class TCPMulServerReceiver extends Thread
	{
		String name;
		DataOutputStream output;
		DataInputStream input;
		public TCPMulServerReceiver(Socket client) {
			try {
				name = "["+client.getInetAddress()+"]";
				output = new DataOutputStream(client.getOutputStream());
				input = new DataInputStream(client.getInputStream());
					
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			
			try {
				name = input.readUTF();
				sendToAll("#"+name+"���� �����Ͽ����ϴ�.");
				clients.put(name, output);
				
				System.out.println("���������ڼ�:"+clients.size());
				
				while(input!=null)
				{
					sendToAll(input.readUTF());
					System.out.println(input.readUTF());
				}
			} catch (IOException e) {
				clients.remove(name);
				sendToAll("#"+name+"���� �����ϼ̽��ϴ�.");
				System.out.println("���������ڼ�:"+clients.size());
			}
		
		}// run ����
	}
	void sendToAll(String msg)
	{
		Iterator it =clients.keySet().iterator();
		while(it.hasNext())
		{
			try {
				DataOutputStream out = 
						(DataOutputStream)clients.get(it.next());
				System.out.println(msg);

				out.writeUTF(msg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new ServerMain();
	}
}