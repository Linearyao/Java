package work;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class census {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		String path;
		while(true){
			System.out.println("请输入文件的路径: ");
			path = in.next();
			if(Files.exists(Paths.get(path))){
				break;
			}else{
				System.out.println("该路径不合法，请重新输入");
			}
			
		}
		//记录单词总个数
		double count = 0;
		BufferedReader reader =  new BufferedReader(new FileReader(path));
		HashMap<String, Double> map = new HashMap<>();
		String str = null;
		//逐行读取、分解为各个单词并加入map中
		while((str=reader.readLine())!=null){
			String[] strs = str.split("[^a-zA-Z']+");
			count += strs.length;
			for(String s: strs){			
				if(!map.containsKey(s)){
					map.put(s, (double) 1);
				}else{
					double n = map.get(s);
					n++;
					map.put(s, n);
				}
			}
		}
		System.out.println("单词总数: " + count);
		//转为List并按Key值进行排序
		List<Map.Entry<String, Double>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>(){
			@Override
			public int compare(Entry<String, Double> o1,
					Entry<String, Double> o2) {
				return -o1.getValue().compareTo(o2.getValue());
			}			
		});
		//算出每个单词出现的频率，存入List中，并输出
		System.out.println("单词, 出现频率");
		for(Map.Entry<String, Double> m: list){
			m.setValue(m.getValue() / count);
			System.out.println(m.getKey() + "  " + m.getValue());
		}
	}
}

