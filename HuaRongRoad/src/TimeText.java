import javax.swing.JTextField;

class TimeText extends JTextField implements Runnable{
 	private Thread th;
 	private javax.swing.Timer t;
 	public TimeText(){
 		super("0",6);
 		setHorizontalAlignment(JTextField.CENTER);
 		setEditable(false);
 		th=new Thread(this);
 		th.start();
 		setStep();
 	}
 	public void setNew(){
 		setStep();
 		setText("0");
 	}
 	public void setRun(){
 		th.resume();
 		//t.restart();
 	}
 	public void setStep(){
 		th.suspend();
 		//t.stop();
 	}
 	/*public void actionPerformed(ActionEvent e){
 		try{
 				setText(""+(Integer.parseInt(getText())+1));
 			}catch(Exception e2){
 			}
 	}*/
 	public void run(){
 		while(true){
 			try{
 				Thread.sleep(1000);
 				setText(""+(Integer.parseInt(getText())+1));
 			}catch(Exception e){
 			}
 		}
 	}
 }