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
	            in.writeUTF("��������:189Ԫ  ��ţ�⣺35.5Ԫ  �峴ɳ�棺 80Ԫ  С��ˣ�10.5Ԫ");
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
