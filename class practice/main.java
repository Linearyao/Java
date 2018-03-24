package work;
import java.util.Scanner;

import work.shape;
import work.tuxing;

public class main {
	public static void main(String[] args) {
		tuxing tx  = new tuxing();
		rectangular rec = new rectangular();
		cube cu = new cube();
		Cone co =new Cone();
		Scanner sc = new Scanner(System.in);
		double a=sc.nextDouble();
		double b=sc.nextDouble();
		double c=sc.nextDouble();
		System.out.println("The area is"+tx.area(a, c));
		System.out.println("The volume is"+tx.vol(a, c));
		System.out.println("The rectangular volume is"+rec.vol(a, b, c));
		System.out.println("The rectangular area is"+rec.area(a, b, c));
		System.out.println("The cube volume is"+cu.vol(a, b, c));
		System.out.println("The cube area is"+cu.area(a, b, c));
		System.out.println("The cone area is"+co.area(a, c));
		System.out.println("The cone volume is"+co.vol(a, c));
	}
}