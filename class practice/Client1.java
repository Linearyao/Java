package work;

import java.io.*;
import java.net.*;
 
class clientThread extends Thread{
	private Socket socket;
	private int number1;
	clientThread(Socket socket,int number1){
		this.socket=socket;
		this.number1=number1;
	}
	public void run() {
		try {
		DataOutputStream in =null;
        DataInputStream out=null;
        in = new DataOutputStream(socket.getOutputStream());
        in.writeInt(number1);

        out=new DataInputStream(socket.getInputStream());
        String result1=out.readUTF();
        System.out.println("C猜的數是："+number1+result1);
        if (result1.equals("猜对了")) {
        	System.out.println("*********************************************");
            in.close();
            out.close(); 
            socket.close();
            System.exit(0);
        }
        sleep(200);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}
}
public class Client1 {
    private static int port = 4200;
    public static void main(String[] args) {
        Socket socket = null;
        try {
        	for(;;) {
        		socket = new Socket("127.0.0.1", port);
        		port++;
        		int number1=(int)(Math.random()*101); 
        		new clientThread (socket,number1).start(); 
        		
        	}
            
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}

