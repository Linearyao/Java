package work;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Socket{

	    private static final int port = 16;
	    public static void main(String[] args) {
	        Socket socket = null;
	        try {
	            socket = new Socket("127.0.0.1", port);
	            DataOutputStream in = new DataOutputStream(socket.getOutputStream());
	            in.writeUTF("榴莲披萨:189元  炒牛肉：35.5元  清炒沙虫： 80元  小青菜：10.5元");
	            DataInputStream out=new DataInputStream(socket.getInputStream());
	            System.out.println(out.readUTF());
	            if (in != null&out!=null) {
	            	in.close();
	            	out.close();
	            }
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (socket != null) {
	                try {
	                    socket.close();
	                } catch (IOException e) {}
	        }
	    }
	}
}
