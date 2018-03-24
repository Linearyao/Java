package work;

import java.io.*;
import java.net.*;

class ServerThread extends Thread{
	private Socket client;
	private int number;
	public ServerThread(Socket client,int number) {
		this.client=client;
		this.number=number;
	}
	public void run() {
		DataInputStream input=null;	
		DataOutputStream output = null;
		try {
		input=new DataInputStream(client.getInputStream());
			int number1=input.readInt();
			String result="²Â´íÁË"+number;
			if(number==number1) result="²Â¶ÔÁË";
			output = new DataOutputStream(client.getOutputStream());
            output.writeUTF(result);
            if(number==number1) {
            	input.close();
            	output.close();
            }
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

public class Server1 {
	private static int port=4200;
	private static final int number=(int)(Math.random()*101);
	
	public static void main(String[]args) {
		try {
			while(true) {
			ServerSocket server=new ServerSocket(port);
			Socket client=server.accept();
			new ServerThread (client,number).start();  
			port++;
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
