import java.awt.Button;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class HuaRongRoad extends JFrame implements MouseListener, KeyListener, ActionListener {
	Person person[] = new Person[10];
	Button left, right, above, below;
	Button restart = new Button("���¿�ʼ");
	JLabel labeltime = new JLabel("��ʱ��");
	JLabel labelStep = new JLabel("������");
	JMenuBar jmb = new JMenuBar();
	JMenuItem jmiscore, jmiexit, jmihelp, jmiabout;


	int sum = 0; // ��ʾ������
	TimeText time = new TimeText();
	JTextField step = new JTextField();
	JMenu helpMenu;
	
	public static String gamer;
	public static int usetime;

	public HuaRongRoad() {
		setTitle("���ݵ�");
		JMenuBar jmb = new JMenuBar();
		setJMenuBar(jmb);
		JMenu gameMenu = new JMenu("��Ϸ");
		helpMenu = new JMenu("����");
		jmb.add(gameMenu);
		jmb.add(helpMenu);
		gameMenu.add(jmiscore = new JMenuItem("�ɼ����а�"));
		gameMenu.add(jmiexit = new JMenuItem("�˳�"));
		gameMenu.addSeparator();
		helpMenu.add(jmihelp = new JMenuItem("����"));
		helpMenu.add(jmiabout = new JMenuItem("����"));
		init();
		setBounds(100, 100, 320, 360);
		setVisible(true);
		validate();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		// private JMenuBar createMenubar(){
		// JMenuBar bar=new JMenuBar();
		// JMenu menu=new JMenu("��Ϸ");
		// menu.add(createMenuItem("����Ϸ"));
		// menu.add(createMenuItem("ѡ��"));
		// menu.addSeparator();
		// menu.add(createMenuItem("�˳�"));
		// bar.add(menu);
		// menu=new JMenu("����");
		// menu.add(createMenuItem("��������"));
		// menu.addSeparator();
		// menu.add(createMenuItem("����"));
		// bar.add(menu);
		// return bar;
		// }
		jmiexit.addActionListener(this);
		jmiscore.addActionListener(this);// ʹ�˳�����Ч,��Ӽ����¼�
		jmihelp.addActionListener(this);
		jmiabout.addActionListener(this);
	}
	// public void paint(Graphics g) {
	// // �������ݵ��ı߽�
	// g.setColor(Color.GREEN); // ��ָ��������������������ɫ
	// g.fillRect(49, 49, 5, 260);// left
	// g.fillRect(254, 49, 5, 260);// right
	// g.fillRect(49, 49, 210, 5); // above
	// g.fillRect(49, 304, 210, 5);// below
	// // ��ʾ�ܲ��ӳ�λ�úͰ�������
	// g.setColor(Color.blue);
	// g.drawString("�����Ӧ������,Ȼ�󰴼����ϵ��������Ҽ�ͷ�ƶ�", 100, 20);
	// g.setColor(Color.red);
	// g.drawString("���ܵ���ȢС��", 110, 300);
	// }

	public void init() {
		setLayout(null);

		add(restart);
		restart.setBounds(100, 320, 120, 25);
		restart.addActionListener(this);
		Music mu = new Music();
		time.setRun();
		String name[] = { "�ܲ�", "����", "�ŷ�", "����", "����", "����", "��", "��", "��", "��" };// ������������
		for (int k = 0; k < name.length; k++) {
			person[k] = new Person(k, name[k]);
			person[k].addMouseListener(this);
			person[k].addKeyListener(this);
			add(person[k]);
		}
		// ���������λ�úʹ�С����
		person[0].setBounds(104, 54, 100, 100);
		person[1].setBounds(104, 154, 100, 50);
		person[2].setBounds(54, 154, 50, 100);
		person[3].setBounds(204, 154, 50, 100);
		person[4].setBounds(54, 54, 50, 100);
		person[5].setBounds(204, 54, 50, 100);
		person[6].setBounds(54, 254, 50, 50);
		person[7].setBounds(204, 254, 50, 50);
		person[8].setBounds(104, 204, 50, 50);
		person[9].setBounds(154, 204, 50, 50);
		person[9].requestFocus();
		left = new Button();
		right = new Button();
		above = new Button();
		below = new Button();
		labeltime.setBounds(300, 300, 50, 50);
		labelStep.setBounds(300, 260, 50, 50);
		time.setBounds(340, 315, 50, 20);
		// ���ü��㲽�����ı�������
		step.setBounds(340, 275, 50, 20);
		step.setEditable(false);
		step.setText("0");
		step.setHorizontalAlignment(JTextField.CENTER);
		add(left);
		add(right);
		add(time);
		add(step);
		add(above);
		add(below);
		add(labeltime);
		add(labelStep);
		left.setBounds(49, 49, 5, 260);// �趨�߽�
		right.setBounds(254, 49, 5, 260);
		above.setBounds(49, 49, 210, 5);
		below.setBounds(49, 304, 210, 5);
		validate();
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		Person man = (Person) e.getSource();
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			go(man, below);
//			System.out.print(time);
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			go(man, above);
//			System.out.print(time);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			go(man, left);
//			System.out.print(time);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			go(man, right);
//			System.out.print(time);
		}
		if (person[0].getBounds().y == 204 && person[0].getBounds().x == 54) {
			time.setStep(); // ����ʱ����ͣ
			JOptionPane.showMessageDialog(null, "��ɹ��ˣ�" + "����ʱ��" + time.getText() + " ��", "��ʾ",
					JOptionPane.DEFAULT_OPTION);
			// ������Ϸ�������
			usetime = Integer.parseInt(time.getText());
			gamer = JOptionPane.showInputDialog("�����������ǳ�:");
			Score sc1;
			try {
				sc1 = new Score();
				sc1.insert();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void go(Person man, Button direction) {
		boolean move = true;
		Rectangle manRect = man.getBounds();
		int x = man.getBounds().x;
		int y = man.getBounds().y;
		if (direction == below) {
			y = y + 50;
		} else if (direction == above) {
			y = y - 50;
		} else if (direction == left) {
			x = x - 50;
		} else if (direction == right) {
			x = x + 50;
		}
		manRect.setLocation(x, y);

		Rectangle directionRect = direction.getBounds();
		for (int k = 0; k < 10; k++) {
			Rectangle personRect = person[k].getBounds();
			if ((manRect.intersects(personRect)) && (man.number != k)) {
				move = false;
			}
		}
		if (manRect.intersects(directionRect)) {
			move = false;
		}
		if (move == true) {
			man.setLocation(x, y);
			sum++;
			step.setText(Integer.toString(sum)); // �ƶ��ɹ�ʱ���²������ڵ���ʾ����
		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jmiscore) {
			try {
				Score score = new Score();
				JOptionPane.showMessageDialog(this, score.getResulut());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getSource() == jmiexit) {
			System.exit(0);
		} else if (e.getSource() == jmihelp) {
			Desktop desktop = Desktop.getDesktop();// �����ť����ҳ
			try {
				desktop.browse(new URI("https://jingyan.baidu.com/article/27fa7326be82d046f8271fc5.html"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// System.out.println("help");
		} else if (e.getSource() == jmiabout) {
			JOptionPane.showMessageDialog(null, "java�γ����" + '\n' + "By �ƿ�1155");
		} else if (e.getSource() == restart) {
			dispose();
			new HuaRongRoad();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}