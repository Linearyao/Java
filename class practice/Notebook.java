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
		// 设置主窗体
		JFrame jf = new JFrame("Notebook");
		jf.setBounds(220, 90, 800, 600);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JFrame frame = new JFrame("替换");
		frame.setBounds(220, 90, 300, 250);
		JLabel l1 = new JLabel(" 原字符串");
		frame.add(l1);
		final JTextField tf1 = new JTextField();
		tf1.setColumns(15);
		frame.add(tf1);
		JLabel l2 = new JLabel("替换为");
		final JTextField tf2 = new JTextField();
		tf2.setColumns(15);
		JButton button = new JButton("确定");
		frame.setLayout(new FlowLayout());
		frame.add(l2);
		frame.add(tf2);
		frame.add(button);

		// 设置菜单栏
		JMenuBar jmbar = new JMenuBar();
		jf.setJMenuBar(jmbar);
		// ===============设置文件菜单=====================
		JMenu filemenu = new JMenu("文件(F)");
		JMenuItem newitem = new JMenuItem("新建(N)");
		JMenuItem openitem = new JMenuItem("打开(O)");
		// openitem.setMnemonic('O');
		JMenuItem saveitem = new JMenuItem("保存(S)");
		JMenuItem lsaveitem = new JMenuItem("另存为(A)");
		JMenuItem exititem = new JMenuItem("退出(X)");
		jmbar.add(filemenu);
		filemenu.add(newitem);
		filemenu.add(openitem);
		filemenu.add(saveitem);
		filemenu.add(lsaveitem);
		filemenu.addSeparator();
		filemenu.add(exititem);

		// ===============设置编辑菜单=====================
		JMenu editmenu = new JMenu("编辑(E)");
		JMenuItem cutitem = new JMenuItem("剪切(T)");
		JMenuItem copyitem = new JMenuItem("复制(C)");
		JMenuItem pasteitem = new JMenuItem("粘贴(P)");
		JMenuItem replaceitem = new JMenuItem("替换(R)");
		jmbar.add(editmenu);
		editmenu.add(cutitem);
		editmenu.add(copyitem);
		editmenu.add(pasteitem);
		editmenu.add(replaceitem);

		// ===============设置帮助菜单=====================
		JMenu helpmenu = new JMenu("帮助(H)");
		JMenuItem helpitem = new JMenuItem("Notebook说明(A)");
		jmbar.add(helpmenu);
		helpmenu.add(helpitem);

		final JTextArea text = new JTextArea();
		JScrollPane jsp = new JScrollPane(text);
		jf.add(jsp);
		jf.setVisible(true);

		// ===============设置快捷键=====================
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

		// ===============实现新建功能=====================
		newitem.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (text.getText().equals(""))
					text.setText("");
				else {
					int value = JOptionPane.showConfirmDialog(null, "是否要保存文本？",
							"提示", JOptionPane.YES_NO_OPTION);
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

		// ===============实现打开功能=====================
		openitem.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (text.getText().equals(""))
					nw(text);
				else {
					int value = JOptionPane.showConfirmDialog(null, "是否要保存文本？",
							"提示", JOptionPane.YES_NO_OPTION);
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

		// ===============实现保存功能=====================
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

		// ===============实现另存为功能=====================
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

		// ===============实现退出功能=====================
		exititem.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (text.getText().equals(""))
					System.exit(0);
				else {
					int value = JOptionPane.showConfirmDialog(null, "是否要保存文本？",
							"提示", JOptionPane.YES_NO_OPTION);
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

		// ===============实现剪切功能=====================
		cutitem.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				text.cut();
			}
		});

		// ===============实现复制功能=====================
		copyitem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				text.copy();
			}
		});
		// ===============实现粘贴功能=====================
		pasteitem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				text.paste();
			}
		});
		// ===============实现替换功能=====================
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

		// ===============实现Notebook说明功能================
		helpitem.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				JOptionPane.showOptionDialog(null, "程序名称:\n Notebook \n"
						+ "程序设计:\n Java课程设计   \n" + "简介:\n 一个简单的文字编辑器\n"
						+ " 制作:  软件工程    小Y\n" + "联系方式：******@qq.com",
						"关于Notebook", JOptionPane.DEFAULT_OPTION,
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
