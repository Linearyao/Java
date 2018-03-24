	package work;
	
	import java.awt.*;
	import java.io.*;
	import java.awt.event.*;
	public class buttonfile
	    extends Frame
	    implements ActionListener {
	  FileDialog open = new FileDialog(this, "打开文件",
	                                   FileDialog.LOAD);
	  String fileName;
	  Button b_dakai = new Button("打开");
	  TextArea text = new TextArea();
	  buttonfile() {
	    super("文件的打开");
	    setBounds(400, 200, 400, 300);
	    b_dakai.addActionListener(this);
	    add(text);
	    add(b_dakai, "South");
	    setVisible(true);
	  }
	  public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == b_dakai) {
	      open.show();
	      fileName = open.getDirectory() + open.getFile();
	      if (fileName != null) {
	        readFile(fileName);
	      }
	    }
	  }
	// 这是一个打开文件文件的方法
	  public void readFile(String fileName) {
	    try {
	      File file = new File(fileName);
	      FileReader readIn = new FileReader(file);
	      int size = (int) file.length();
	      int charsRead = 0;
	      char[] content = new char[size];
	      while (readIn.ready()) {
	        charsRead += readIn.read(content, charsRead, size - charsRead);
	      }
	      readIn.close();
	      text.setText(new String(content, 0, charsRead));
	    }
	    catch (IOException e) {
	      System.out.println("Error Opening file");
	    }
	  }
	  public static void main(String args[]) {
	    new buttonfile().addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      };
	    });
	;
	  }
	}