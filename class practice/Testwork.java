package work;

import java.util.*;

public class Testwork {

	private static char[] str;
	public static String checkPassword(String pwdstr) {
		String regexZ = "\\d*";
		String regexS = "[a-zA-Z]+";
		String regexT = "\\W+$";
		String regexZT = "\\D*";
		String regexST = "[\\d\\W]*";
		String regexZS = "\\w*";
		String regexZST = "[\\w\\W]*";

		if (pwdstr.matches(regexZ))
			return "弱";
		if (pwdstr.matches(regexS))
			return "弱";
		if (pwdstr.matches(regexT))
			return "弱";
		if (pwdstr.matches(regexZT))
			return "中";
		if (pwdstr.matches(regexST))
			return "中";
		if (pwdstr.matches(regexZS))
			return "中";
		if (pwdstr.matches(regexZST))
			return "强";
		return pwdstr;
	}

	public static boolean isA(int x) {
		boolean flag = false;
		if (x < str.length - 2 && str[x] == str[x + 1]) {
			isA(++x);
		} else {
			flag = true;
		}
		return flag;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		str = new char[20];
		while(true){
		Scanner sc = new Scanner(System.in);
		String pwd = sc.nextLine();
		//sc.close();
		str = pwd.toCharArray();
		if (str.length < 6) {
			System.out.println("密码太短请重新输入：");
		} else if (isA(0)) {
			System.out.println("密码强度为:" + checkPassword(pwd));
		}else{
			System.out.println("error input!");break;
		}
	}
	}
}
