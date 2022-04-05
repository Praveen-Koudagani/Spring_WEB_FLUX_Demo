package othertasks;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UncheckedExceptionsDemo {

	private static final Logger logg = LogManager.getLogger(UncheckedExceptionsDemo.class);
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		List<Integer> list=null;
		int[] arr= {1,2,3};
		try {
			
			logg.info("enter the a value:");
			int a=sc.nextInt();
			logg.info("enter b value as 0 for Arithmetic exception");
			int b=sc.nextInt();
			int c=a/b;
		} catch (ArithmeticException e) {
			logg.info(e.getLocalizedMessage());
		}
		try {
			list.add(1);
		}
		catch(NullPointerException e) {
		logg.info(e.getMessage());}
		try {
			logg.info(arr[3]);
		} catch (IndexOutOfBoundsException e) {
			logg.info(e.getLocalizedMessage());
		}
		try {
			logg.info("enter float for input mismatch exception");
			int s=sc.nextInt();}
		catch (InputMismatchException e) {
			logg.info(e.toString());
		}
		sc.close();
		
	}
}
