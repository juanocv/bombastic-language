import java.util.Scanner;

public class MainTestClass{ 
	public static void main(String args[]){ 
		Scanner key = new Scanner(System.in);
		double a;
		double b;
		String t1;
		a = key.nextDouble();
		b = key.nextDouble();
		a = 1.567;
		b = 1.568;
		if (a>b) {
		System.out.println(a);
		} else{
		System.out.println(b);
		}
	}
}