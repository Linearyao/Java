	package work;
	
	import java.awt.*;
	import java.io.*;
	import java.awt.event.*;
	public class buttonfile
	    extends Frame
	    implements ActionListener {
	  FileDialog open = new FileDialog(this, "���ļ�",
	                                   FileDialog.LOAD);
	  String fileName;
	  Button b_dakai = new Button("��");
	  TextArea text = new TextArea();
	  buttonfile() {
	    super("�ļ��Ĵ�");
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
	// ����һ�����ļ��ļ��ķ���
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