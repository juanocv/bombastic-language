import java.util.Scanner;

public class MainTestClass{ 
	public static void main(String args[]){ 
		Scanner key = new Scanner(System.in);
		double i;
		i = 0;
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