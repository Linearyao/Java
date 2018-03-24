package work;

/*把下面的代码放入文件名为BombFrame.java的文件里然后后编译运行就可以了。
游戏有一个BUG，当在选项中调整雷区大小，改变和现在的雷区同样大小时，就会出错
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;

public class saolei extends JFrame implements ActionListener{
    private JPanel panel;
    private BombPanel bombPanel;//雷区
    private JButton initButton;
    private JLabel label=new JLabel("雷数:");
    private JTextField text;
    private ItemDialog dialog;
    private TimeText time;
    private boolean isStart;
    public saolei() {
    	super("扫雷");
    	Container c=getContentPane();
    	c.setLayout(new BorderLayout());
    	isStart=false;
    	panel=new JPanel(new FlowLayout(FlowLayout.CENTER));
    	initButton=new JButton("新游戏");
    	initButton.addActionListener(this);
    	panel.add(initButton);
    	c.add(panel,BorderLayout.NORTH);

    	bombPanel=new BombPanel();
    	c.add(bombPanel,BorderLayout.CENTER);

    	panel=new JPanel(new FlowLayout(FlowLayout.CENTER));
    	text=new JTextField("10",10);
    	text.setEditable(false);
    	panel.add(label);
    	panel.add(text);
    	label=new JLabel("用时：");
    	time=new TimeText();
    	panel.add(label);
    	panel.add(time);
    	c.add(panel,BorderLayout.SOUTH);

       	setBounds(50,10,bombPanel.getBWidth()+6,bombPanel.getBHeight()+113);
    	setJMenuBar(createMenubar());
    	dialog=new ItemDialog("选项",bombPanel);
    }
    private JMenuBar createMenubar(){
    	JMenuBar bar=new JMenuBar();
    	JMenu menu=new JMenu("游戏");
    	menu.add(createMenuItem("新游戏"));
    	menu.add(createMenuItem("选项"));
    	menu.addSeparator();
    	menu.add(createMenuItem("退出"));
    	bar.add(menu);
    	menu=new JMenu("帮助");
    	menu.add(createMenuItem("帮助主题"));
    	menu.addSeparator();
    	menu.add(createMenuItem("关于"));
    	bar.add(menu);
    	return bar;
    }
    private JMenuItem createMenuItem(String name){
    	JMenuItem menu=new JMenuItem(name);
    	menu.addActionListener(this);
    	return menu;
    }
    public void actionPerformed(ActionEvent e){
    	if(e.getActionCommand().equals("新游戏")){
    		bombPanel.startNew();
    		bombPanel.repaint();
    		text.setText(""+bombPanel.getBombNumber());
    		time.setNew();
    	}
    	if(e.getActionCommand().equals("选项")){
    		dialog.setVisible(true);
    	}
    	if(e.getActionCommand().equals("退出")){
    		System.exit(0);
    	}
    	if(e.getActionCommand().equals("关于")){
    		JOptionPane.showMessageDialog(null,"JAVA扫雷游戏\n作者：苗壮 PISOCEAN",
    		"关于",JOptionPane.PLAIN_MESSAGE);
    	}
    	if(e.getActionCommand().equals("帮助主题")){
    		JOptionPane.showMessageDialog(null,"扫雷不会玩吗？\n想学啊你，找人教你！",
    		"帮助主题",JOptionPane.PLAIN_MESSAGE);
    	}

    }
    public static void main(String[] args) {
    	try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}catch(Exception e){}
       JFrame frame=new saolei();
       frame.setVisible(true);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setResizable(false);
    }
    //有两种方式实现计时，Thread与Timer类
    class TimeText extends JTextField implements Runnable/*ActionListener*/{
    	private Thread th;
    	private javax.swing.Timer t;
    	public TimeText(){
    		super("0",6);
    		setHorizontalAlignment(JTextField.CENTER);
    		setEditable(false);
    		th=new Thread(this);
    		th.start();
    		//t=new javax.swing.Timer(1000,this);
    		//t.start();
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
    class ItemDialog extends JDialog implements ActionListener,ItemListener{//选项对话框，用来选择雷区大小与雷数
    	private JRadioButton priB,midB,advB,selfB;
    	private JTextField hField,lField,numField;
    	private JButton ok,cancel;
    	BombPanel bPanel;
    	private int num;
    	public ItemDialog(String name,BombPanel bPanel){
    		super(saolei.this,name);
    		this.bPanel=bPanel;
    		num=1;
    		setBounds(300,100,400,400);
    		JPanel panel=new JPanel(null);
    		add(panel,BorderLayout.CENTER);
    		panel.setBorder(BorderFactory.createTitledBorder("难度"));
    		addWindowListener(new WindowAdapter(){
    				public void windowClosing(WindowEvent e){
    					setVisible(false);
    				}
    			});
    		priB=createRadioButton("初级，9*9，10个雷",true,40,40,200,40);
    		midB=createRadioButton("中级，16*16，40个雷",false,40,80,200,40);
    		advB=createRadioButton("高级，16*30，99个雷",false,40,120,200,40);
    		selfB=createRadioButton("自定义",false,40,160,200,40);
    		ButtonGroup group=new ButtonGroup();
    		group.add(priB);group.add(midB);group.add(advB);group.add(selfB);
    		panel.add(priB);panel.add(midB);panel.add(advB);panel.add(selfB);
    		panel.add(createLabel("行数：",40,200,50,40));
    		panel.add(createLabel("列数：",40,240,50,40));
    		panel.add(createLabel("雷数：",40,280,50,40));
    		hField=createTextField("",90,210,100,20);
    		hField.setEnabled(false);
    		lField=createTextField("",90,250,100,20);
    		lField.setEnabled(false);
    		numField=createTextField("",90,290,100,20);
    		numField.setEnabled(false);
    		panel.add(hField);panel.add(lField);panel.add(numField);
    		ok=new JButton("确定");ok.setBounds(280,250,70,20);
    		ok.addActionListener(this);panel.add(ok);
    		cancel=new JButton("取消");cancel.setBounds(280,290,70,20);
    		cancel.addActionListener(this);panel.add(cancel);
    	}
    	private JRadioButton createRadioButton(String s,boolean sel,int x,int y,int w,int h){
    		JRadioButton button=new JRadioButton(s,sel);
    		button.setBounds(x,y,w,h);
    		button.addItemListener(this);
    		return button;
    	}
    	private JLabel createLabel(String s,int x,int y,int w,int h){
    		JLabel label=new JLabel(s);
    		label.setBounds(x,y,w,h);
    		return label;
    	}
    	private JTextField createTextField(String s,int x,int y,int w,int h){
    		JTextField field=new JTextField(s);
    		field.setBounds(x,y,w,h);
    		return field;
    	}
    	public void actionPerformed(ActionEvent e){
    		if(e.getActionCommand().equals("确定")){
    			if(priB.isSelected()){
    				if(num==1){
    					setVisible(false);
    					return;
    				}
    				bPanel.setXYNum(9,9,10);
    				saolei.this.setBounds(50,10,bPanel.getBWidth()+6,bPanel.getBHeight()+113);
    				text.setText(""+bPanel.getBombNumber());
    				num=1;
    			}
    			if(midB.isSelected()){
    				if(num==2){
    					setVisible(false);
    					return;
    				}
    				bPanel.setXYNum(16,16,40);
    				saolei.this.setBounds(50,10,bPanel.getBWidth()+6,bPanel.getBHeight()+113);
    				text.setText(""+bPanel.getBombNumber());
    				num=2;
    			}
    			if(advB.isSelected()){
    				if(num==3){
    					setVisible(false);
    					return;
    				}
    				bPanel.setXYNum(16,30,99);
    				saolei.this.setBounds(50,10,bPanel.getBWidth()+6,bPanel.getBHeight()+113);
    				text.setText(""+bPanel.getBombNumber());
    				num=3;
    			}
    			if(selfB.isSelected()){
    				int a=0,b=0,c=0;
    				try{
    					a=Integer.parseInt(hField.getText());
    					b=Integer.parseInt(lField.getText());
    					c=Integer.parseInt(numField.getText());
    					if(a>=18||b>=32||c>=a*b)throw new NumberFormatException();
    				}catch(NumberFormatException ee){
    					JOptionPane.showMessageDialog(null,"输入有误或者超出范围！");
    					hField.setText("");
    					lField.setText("");
    					numField.setText("");
    					return;
    				}
    				bPanel.setXYNum(a,b,c);
    				saolei.this.setBounds(50,10,bPanel.getBWidth()+6,bPanel.getBHeight()+113);
    				//bPanel.startNew();
    				text.setText(""+bPanel.getBombNumber());
    			}
    			setVisible(false);
    			time.setNew();
    			bPanel.startNew();
    		}
    		if(e.getActionCommand().equals("取消")){
    			setVisible(false);
    		}
    	}
    	public void itemStateChanged(ItemEvent e){
    		hField.setEnabled(selfB.isSelected());
    		lField.setEnabled(selfB.isSelected());
    		numField.setEnabled(selfB.isSelected());
    	}
    }
    class BombPanel extends JPanel implements ActionListener{
    	BombButton[][] bombs;
    	public final static int SIZE=38;
    	public final int[] a={-1,-1,-1,0,1,1,1,0},b={-1,0,1,1,1,0,-1,-1};
    	private int h,l;
    	private int bombNum;
    	public BombPanel(){
    		this(9,9);//默认为9*9
    	}
    	public BombPanel(int h,int l){
    		this(h,l,10);//默认为9*9,十个雷
    	}
    	public BombPanel(int h,int l,int num){
    		super();
    		this.h=h;
    		this.l=l;
    		this.bombNum=num;
    		init();
    	}
    	public int getBombNumber(){
    		return bombNum;
    	}
    	public void setXYNum(int h,int l,int num){
    		this.h=h;
    		this.l=l;
    		this.bombNum=num;
    		removeAll();
    		init();
    	}
    	public void startNew(){
    		for(int i=0;i<h;i++)
    			for(int j=0;j<l;j++){
    				bombs[i][j].setEnabled(true);
    				bombs[i][j].setBomb(false);
    				bombs[i][j].setMarked(false);
    				bombs[i][j].setBombNumber(0);
    				bombs[i][j].setText("");
    				bombs[i][j].setForeground(Color.BLACK);
    			}
    		addBomb();
    		isStart=false;
    	}
    	private void init(){
    		setLayout(new GridLayout(h,l));
    		bombs=new BombButton[h][l];
    		for(int i=0;i<h;i++)
    			for(int j=0;j<l;j++){
    				bombs[i][j]=new BombButton(i,j);
    				bombs[i][j].addActionListener(this);
    				bombs[i][j].addMouseListener(new MouseAdapter(){
    							public void mouseClicked(MouseEvent e){
    								BombButton button=(BombButton)e.getSource();
    								if(e.getButton()==MouseEvent.BUTTON3){
										if(isWin())return;
    									if(button.isEnabled()){
    										if(button.isMarked()){
    											try{
    												int n=Integer.parseInt(text.getText());
    												text.setText(""+(n+1));
    											}catch(Exception ee){}
    											button.setMarked(false);
    											button.setText("");
    										}
    										else{
    											try{
    												int n=Integer.parseInt(text.getText());
    												text.setText(""+(n-1));
    											}catch(Exception ee){}
    											button.setMarked(true);
    											button.setText("F");
    										}
    									}
									}
	    						}
    					});
    				add(bombs[i][j]);
    			}
    		addBomb();
    	}
    	private void addBomb(){
    		int n=bombNum;
    		int i,j;
    		do{
    			i=(int)(Math.random()*h);
    			j=(int)(Math.random()*l);
    			if(bombs[i][j].isBomb())continue;
    			else {bombs[i][j].setBomb(true);
    				n--;
    			}
    		}while(n>0);
    		for(i=0;i<h;i++)
    			for(j=0;j<l;j++){
    				bombs[i][j].setBombNumber(getRoundBombNum(i,j));
    			}
    	}
    	private int getRoundBombNum(int i,int j){
    		int n=0;
    		for(int k=0;k<8;k++){
    			if(i+a[k]<0||i+a[k]>h-1||j+b[k]<0||j+b[k]>l-1)
    				continue;
    			else if(bombs[i+a[k]][j+b[k]].isBomb())n++;
    		}
    		return n;
    	}
    	private void turn(int hn,int ln){
    		if(hn<0||hn>h-1||ln<0||ln>l-1||!bombs[hn][ln].isEnabled()||bombs[hn][ln].isMarked())return;
    		if(bombs[hn][ln].isBomb()){
    			turnAll();
    			time.setStep();
    			JOptionPane.showMessageDialog(null,"踩中地雷了！");
    			//startNew();//踩中地雷是否重新开始
    		}
    		else{
    			if(bombs[hn][ln].getBombNumber()==0){
    				bombs[hn][ln].setEnabled(false);
    				for(int k=0;k<8;k++)
    					turn(hn+a[k],ln+b[k]);
    			}
    			else{
    				bombs[hn][ln].setEnabled(false);
    				bombs[hn][ln].setText(""+bombs[hn][ln].getBombNumber());
    			}
    		}
    		if(isWin()){
			    	turnAll();
			    	time.setStep();
			    	JOptionPane.showMessageDialog(null,"你挖完了所有的雷！\n用时："+time.getText());
    		}
    	}
    	private void turnAll(){
    		for(int i=0;i<h;i++)
    			for(int j=0;j<l;j++){
    				BombButton button=bombs[i][j];
    				if(!button.isEnabled())continue;
    				else{
    					if(button.isBomb()){
    						button.setEnabled(false);
    						button.setForeground(Color.RED);
    						button.setText("B");
    					}
    					else{
    						button.setEnabled(false);
    						if(button.getBombNumber()!=0)
    							button.setText(""+button.getBombNumber());
    					}
    				}
    			}
    	}
    	private boolean isWin(){
    		int n=0;
    		for(int i=0;i<h;i++)
    			for(int j=0;j<l;j++){
    				if(bombs[i][j].isEnabled())n++;
    			}
    		if(n==bombNum)return true;
    		else return false;
    	}
    	public int getBWidth(){
    		return l*SIZE;
    	}
    	public int getBHeight(){
    		return h*SIZE;
    	}
    	public void actionPerformed(ActionEvent e){
    		BombButton button=(BombButton)e.getSource();
    		turn(button.getH(),button.getL());
    		//JOptionPane.showMessageDialog(null,""+isStart);
    		if(!isStart){
    			time.setRun();
    			isStart=true;
    		}
    	}
    }
}
class BombButton extends JButton{
	private int h,l;
	private boolean isBomb;
	private boolean isMarked;
	private int bombNumber;
	public BombButton(){
		this(0,0);
	}
	public BombButton(int h,int l){
		super();
		this.h=h;
		this.l=l;
		isBomb=false;
		isMarked=false;
		bombNumber=0;
		setFont(new Font("dialog",Font.PLAIN,9));
		setForeground(Color.BLACK);
		setMargin(null);
	}
	public int getH(){
		return h;
	}
	public int getL(){
		return l;
	}
	public boolean isBomb(){
		return isBomb;
	}
	public void setBomb(boolean bomb){
		isBomb=bomb;
	}
	public boolean isMarked(){
		return isMarked;
	}
	public void setMarked(boolean mark){
		isMarked=mark;
	}
	public void setBombNumber(int num){
		bombNumber=num;
	}
	public int getBombNumber(){
		return bombNumber;
	}
}
