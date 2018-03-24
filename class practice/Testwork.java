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
			return "��";
		if (pwdstr.matches(regexS))
			return "��";
		if (pwdstr.matches(regexT))
			return "��";
		if (pwdstr.matches(regexZT))
			return "��";
		if (pwdstr.matches(regexST))
			return "��";
		if (pwdstr.matches(regexZS))
			return "��";
		if (pwdstr.matches(regexZST))
			return "ǿ";
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
			System.out.println("����̫�����������룺");
		} else if (isA(0)) {
			System.out.println("����ǿ��Ϊ:" + checkPassword(pwd));
		}else{
			System.out.println("error input!");break;
		}
	}
	}
}
