package work;

public class Menu {
	double pizza = 189;
	double beef = 35.5;
	double sandworm = 80;
	double cabbage = 10.5;
	double sum = pizza+beef+sandworm+cabbage;

public static void main(String[] args) {
	Menu m = new Menu();
	System.out.println("��������:"+m.pizza+"Ԫ");
	System.out.println("��ţ�⣺"+m.beef+"Ԫ");
	System.out.println("�峴ɳ��"+m.sandworm+"Ԫ");
	System.out.println("С���"+m.cabbage+"Ԫ");
	System.out.println("�ܼ۸�Ϊ��"+m.pizza+"+"+m.beef+"+"+m.sandworm+"+"+m.cabbage+"="+m.sum+"Ԫ");
}
}