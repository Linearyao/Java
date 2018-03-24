package work;

import java.io.IOException;
import java.util.Timer;
public class Time {
public static void main(String[] args){
Timer timer = new Timer();
timer.schedule(new MyTask(), 1000, 2000);//在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
while(true){//这个是用来停止此任务的,否则就一直循环执行此任务了
try {
int ch = System.in.read();
if(ch-'c'==0){
timer.cancel();//使用这个方法退出任务
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
//你要进行的操作
}
}
}