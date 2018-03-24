package work;

import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Notebook {

	static String path = "";

	public static void main(String[] args) {
		// ����������
		JFrame jf = new JFrame("Notebook");
		jf.setBounds(220, 90, 800, 600);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JFrame frame = new JFrame("�滻");
		frame.setBounds(220, 90, 300, 250);
		JLabel l1 = new JLabel(" ԭ�ַ���");
		frame.add(l1);
		final JTextField tf1 = new JTextField();
		tf1.setColumns(15);
		frame.add(tf1);
		JLabel l2 = new JLabel("�滻Ϊ");
		final JTextField tf2 = new JTextField();
		tf2.setColumns(15);
		JButton button = new JButton("ȷ��");
		frame.setLayout(new FlowLayout());
		frame.add(l2);
		frame.add(tf2);
		frame.add(button);

		// ���ò˵���
		JMenuBar jmbar = new JMenuBar();
		jf.setJMenuBar(jmbar);
		// ===============�����ļ��˵�=====================
		JMenu filemenu = new JMenu("�ļ�(F)");
		JMenuItem newitem = new JMenuItem("�½�(N)");
		JMenuItem openitem = new JMenuItem("��(O)");
		// openitem.setMnemonic('O');
		JMenuItem saveitem = new JMenuItem("����(S)");
		JMenuItem lsaveitem = new JMenuItem("���Ϊ(A)");
		JMenuItem exititem = new JMenuItem("�˳�(X)");
		jmbar.add(filemenu);
		filemenu.add(newitem);
		filemenu.add(openitem);
		filemenu.add(saveitem);
		filemenu.add(lsaveitem);
		filemenu.addSeparator();
		filemenu.add(exititem);

		// ===============���ñ༭�˵�=====================
		JMenu editmenu = new JMenu("�༭(E)");
		JMenuItem cutitem = new JMenuItem("����(T)");
		JMenuItem copyitem = new JMenuItem("����(C)");
		JMenuItem pasteitem = new JMenuItem("ճ��(P)");
		JMenuItem replaceitem = new JMenuItem("�滻(R)");
		jmbar.add(editmenu);
		editmenu.add(cutitem);
		editmenu.add(copyitem);
		editmenu.add(pasteitem);
		editmenu.add(replaceitem);

		// ===============���ð����˵�=====================
		JMenu helpmenu = new JMenu("����(H)");
		JMenuItem helpitem = new JMenuItem("Notebook˵��(A)");
		jmbar.add(helpmenu);
		helpmenu.add(helpitem);

		final JTextArea text = new JTextArea();
		JScrollPane jsp = new JScrollPane(text);
		jf.add(jsp);
		jf.setVisible(true);

		// ===============���ÿ�ݼ�=====================
		newitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				Event.CTRL_MASK));
		openitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				Event.CTRL_MASK));
		saveitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				Event.CTRL_MASK));
		cutitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				Event.CTRL_MASK));
		copyitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				Event.CTRL_MASK));
		pasteitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				Event.CTRL_MASK));
		replaceitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				Event.CTRL_MASK));

		// ===============ʵ���½�����=====================
		newitem.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (text.getText().equals(""))
					text.setText("");
				else {
					int value = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ�����ı���",
							"��ʾ", JOptionPane.YES_NO_OPTION);
					String text_ = text.getText();
					if (value == JOptionPane.YES_OPTION)
						try {
							lsave(text_);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					text.setText("");
				}

			}
		});

		// ===============ʵ�ִ򿪹���=====================
		openitem.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (text.getText().equals(""))
					nw(text);
				else {
					int value = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ�����ı���",
							"��ʾ", JOptionPane.YES_NO_OPTION);
					String text_ = text.getText();
					if (value == JOptionPane.YES_OPTION)
						try {
							lsave(text_);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					else if (value == JOptionPane.NO_OPTION)
						text.setText("");
					else
						return;
					nw(text);
				}

			}

		});

		// ===============ʵ�ֱ��湦��=====================
		saveitem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String text_ = text.getText();
				try {
					save(text_);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		// ===============ʵ�����Ϊ����=====================
		lsaveitem.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {

				String text_ = text.getText();
				try {
					lsave(text_);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		// ===============ʵ���˳�����=====================
		exititem.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (text.getText().equals(""))
					System.exit(0);
				else {
					int value = JOptionPane.showConfirmDialog(null, "�Ƿ�Ҫ�����ı���",
							"��ʾ", JOptionPane.YES_NO_OPTION);
					String text_ = text.getText();
					if (value == JOptionPane.YES_OPTION)
						try {
							lsave(text_);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					System.exit(0);
				}

			}

		});

		// ===============ʵ�ּ��й���=====================
		cutitem.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				text.cut();
			}
		});

		// ===============ʵ�ָ��ƹ���=====================
		copyitem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				text.copy();
			}
		});
		// ===============ʵ��ճ������=====================
		pasteitem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				text.paste();
			}
		});
		// ===============ʵ���滻����=====================
		replaceitem.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				frame.setVisible(true);

			}

		});
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				String reptext = text.getText().replaceAll(tf1.getText(),
						tf2.getText());
				text.setText("");
				text.append(reptext);

			}

		});

		// ===============ʵ��Notebook˵������================
		helpitem.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				JOptionPane.showOptionDialog(null, "��������:\n Notebook \n"
						+ "�������:\n Java�γ����   \n" + "���:\n һ���򵥵����ֱ༭��\n"
						+ " ����:  �������    СY\n" + "��ϵ��ʽ��******@qq.com",
						"����Notebook", JOptionPane.DEFAULT_OPTION,
						JOptionPane.INFORMATION_MESSAGE, null, null, null);
			}
		});

	}// Main

	public static void openfile(File file, JTextArea text) throws IOException {

		BufferedReader bufr = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = bufr.readLine()) != null) {
			text.append(line);
			text.append("\r\n");
		}
		bufr.close();

	}

	public static void nw(JTextArea text) {
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			String path = chooser.getSelectedFile().getAbsolutePath();
			File file = new File(path);
			try {
				openfile(file, text);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static void lsave(String text) throws IOException {
		JFileChooser chooser = new JFileChooser();
		File file = null;
		if (chooser.showSaveDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			{
				path = chooser.getSelectedFile().getAbsolutePath();
				file = new File(path);
				savefile(file, text);
			}
		}
	}

	public static void save(String text) throws IOException {

		JFileChooser chooser = new JFileChooser();
		File file = null;
		if (path.equals("")) {
			if (chooser.showSaveDialog(chooser) == JFileChooser.APPROVE_OPTION) {
				path = chooser.getSelectedFile().getAbsolutePath();
			} else
				return;
		}
		file = new File(path);
		savefile(file, text);

	}

	public static void savefile(File file, String text) throws IOException {

		byte[] b = text.getBytes();
		if (file != null) {
			BufferedOutputStream bufw = new BufferedOutputStream(
					new FileOutputStream(file));
			bufw.write(b, 0, b.length);
			bufw.close();
		}
	}
}
