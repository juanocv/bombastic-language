package LinguagemGeradas;

import java.util.Scanner;

public class MainTestClass{ 
	public static void main(String args[]){ 
		Scanner key = new Scanner(System.in);
		double a;
		double b;
		double i;
		i = 0;
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
		do {
			System.out.println(i);
			i = i+1;
		} while (i<3 || i>4);

		do {
			if (a>b && a<b || a>b) {
			System.out.println(a);
		} else{
			System.out.println(b);
		}			i = i+1;
		} while (i<3 || i>4);

		i = 0;
		do {
			System.out.println(i);
			i = i+1;
		} while (i<3+3 && i>3);

		key.close();
	}
}