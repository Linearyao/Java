package work;

import java.io.IOException;
import java.util.Timer;
public class Time {
public static void main(String[] args){
Timer timer = new Timer();
timer.schedule(new MyTask(), 1000, 2000);//��1���ִ�д�����,ÿ�μ��2��,�������һ��Data����,�Ϳ�����ĳ���̶���ʱ��ִ���������.
while(true){//���������ֹͣ�������,�����һֱѭ��ִ�д�������
try {
int ch = System.in.read();
if(ch-'c'==0){
timer.cancel();//ʹ����������˳�����
}
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
}
static class MyTask extends java.util.TimerTask{
@Override
public void run() {
//��Ҫ���еĲ���
}
}
}