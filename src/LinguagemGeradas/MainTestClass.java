package LinguagemGeradas;

import java.util.Scanner;

public class MainTestClass{ 
	public static void main(String args[]){ 
		Scanner key = new Scanner(System.in);
		double a;
		double b;
		a = 1;
		b = 10;
		if (a>b && a<b || a>b) {
			System.out.println(a);
		} else{
			System.out.println(b);
		}
		if (a==1 || a>b) {
			System.out.println(a);
		} else{
			System.out.println(b);
		}
	}
}