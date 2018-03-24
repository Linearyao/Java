package work;

import java.io.*;
import java.net.*;
import java.util.regex.*;

public class Server {
	public static final int port=16;
	
	public static void main(String[]args) {
		try {
			ServerSocket server=new ServerSocket(port);
			Socket client=server.accept();
			DataInputStream input=null;
			input=new DataInputStream(client.getInputStream());
			double sum=0;
			String list=input.readUTF();
			double discount = 0.9+Math.random()*0.1;
			Pattern p=Pattern.compile("[0-9]{1,}(.)?[0-9]{1,}");
			Matcher m=p.matcher(list);
			while(m.find()) {
				String order=m.group();
				sum+=Float.parseFloat(order);
			}
			
			DataOutputStream output = null;
			output = new DataOutputStream(client.getOutputStream());
            output.writeUTF("ÄãµÄÕËµ¥:\n"+list+"\n"+"ÕÛ¿Û£º"+discount+"£¬Ó¦¸¶£º"
            		        +sum+"*"+discount+"="+(sum*discount));
            if (output != null&input!=null) {
            	output.close();
            	input.close();
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
