package LinguagemGeradas;

import java.util.Scanner;

public class MainTestClass{ 
	public static void main(String args[]){ 
		Scanner key = new Scanner(System.in);
		double a;
		double b;
		char c;
		double i;
		double unused;
		String t1;
		a = key.nextDouble();
		b = key.nextDouble();
		i = 0;
		a = 1;
		b = 10;
		t1 = "teste";
		c = 't';
		System.out.println(t1);

		System.out.println(c);

		if (a>b) {
			System.out.println(a);
			System.out.println(a);
			System.out.println(a);
		} else{
			System.out.println(b);
			System.out.println(a);
			System.out.println(a);
		}
		while (i<3) {
			System.out.println(i);
			i = i+1;
		}

		i = 0;
		do {
			System.out.println(i);
			i = i+1;
		} while (i<3);

	}
}