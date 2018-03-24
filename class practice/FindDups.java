package work;

import java.util.*;

public class FindDups {
	public static void main(String[] args) {
		Set<String> uniques =new HashSet<>();
		Set<String> dups = new HashSet<>();
		String a = "i came i saw i left";
		for(String a1 : args)
			if(!uniques.add(a1))
				dups.add(a1);
		uniques.removeAll(dups);
		System.out.println("repeat words:");
		System.out.println("not repeat words:");
	}
}
