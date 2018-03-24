package work;

public class Menu {
	double pizza = 189;
	double beef = 35.5;
	double sandworm = 80;
	double cabbage = 10.5;
	double sum = pizza+beef+sandworm+cabbage;

public static void main(String[] args) {
	Menu m = new Menu();
	System.out.println("榴莲披萨:"+m.pizza+"元");
	System.out.println("炒牛肉："+m.beef+"元");
	System.out.println("清炒沙虫"+m.sandworm+"元");
	System.out.println("小青菜"+m.cabbage+"元");
	System.out.println("总价格为："+m.pizza+"+"+m.beef+"+"+m.sandworm+"+"+m.cabbage+"="+m.sum+"元");
}
}