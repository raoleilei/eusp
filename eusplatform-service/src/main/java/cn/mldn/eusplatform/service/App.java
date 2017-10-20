package cn.mldn.eusplatform.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
	public static Map<String,Long> map = new HashMap<String,Long>() ;
	public static void main(String[] args) throws Exception {
		map.put("java",0l) ;
		map.put("jsp",0l) ;
		File file = new File("E:" + File.separator + "eusplatformWorkspace" + File.separator + "eusplatform") ;
		allRows(file) ;
		System.out.println(map.get("java"));
		System.out.println(map.get("jsp"));
		System.out.println(map.get("java") + map.get("jsp"));
	}
	public static void allRows(File file) throws Exception{
		if(file.isDirectory()) {
			File list[] = file.listFiles() ;
			for(int i = 0 ; i < list.length ; i++) {
				allRows(list[i]) ;
			}
		}else {
			String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1) ;
			switch(suffix) {
			case "java" : {
				long count = map.get("java") ;
				map.put("java", count + countRows(file)) ;
				break ;
			}
			case "jsp" : {
				long count = map.get("jsp") ;
				map.put("jsp", count + countRows(file)) ;
				break ;
			}
			}
		}
	}
	public static long countRows(File file) throws Exception{
		long count = 0l ;
		Scanner scan = new Scanner(file) ;
		scan.useDelimiter("\n");
		while(scan.hasNext()) {
			if(scan.next().trim().length() > 0 ) {
				count ++ ;
			}
		}
		scan.close();
		return count ;
	}
}
