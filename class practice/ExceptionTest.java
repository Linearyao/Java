package work;
import java.util.Scanner;

public class ExceptionTest{
	int N;
	int x;
	int[] b = new int[5];
	private static Scanner sc;
	public ExceptionTest(int N,int x){
		this.N = N;
		this.x = x;
	}
	public int factorial(){
		if(N==0)
			return 1;
		int result = 1;
		for(int i=1;i<=N;i++)
			result *=i;
		return result;
	}
	public void initial(){
		for(int i=0;i<5;i++)
			{
			b[i] =(int)(Math.random()*5);
			System.out.print(b[i]+" ");
			}
		System.out.println();
	}
	public double calculate() throws ArithmeticException {
		initial();
	    return factorial()+1000/b[x];
	}
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		try{
			int d1 = sc.nextInt();
		    int d2 = sc.nextInt();
		    if(d1<0||d1>32)
		    	throw new MyException(d1);
		    ExceptionTest et = new ExceptionTest(d1,d2);	
			System.out.println("\n"+et.calculate());
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e.toString());
		}catch(MyException e){
			System.out.println(e.toString());
		}catch(ArithmeticException e){
			System.out.println(e.toString());
		}
	}

}