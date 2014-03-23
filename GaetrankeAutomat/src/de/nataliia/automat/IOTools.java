package de.nataliia.automat;

import java.util.Scanner;

public class IOTools {
	
	public static double readDouble(String text){
		Scanner scanner = new Scanner(System.in);
		System.out.print(text);
		return Double.parseDouble(scanner.nextLine());
		
	}

	public static String readLine(String string) {
		System.out.print(string);
		return new Scanner(System.in).nextLine();
	}
}
