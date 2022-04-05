package othertasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MaxSubArray {

	private static final Logger logg = LogManager.getLogger(MaxSubArray.class);

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		logg.info("enter number of elements:");
		int n = sc.nextInt();
		List<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int temp = sc.nextInt();
			numbers.add(temp);
		}
		int ans=0,currentsum=0;
		int sum=0;
		int a=0,st=0,ed=0;
		while(a<n) {
			sum=sum+numbers.get(a);
			if((currentsum+numbers.get(a))<=0) {
				currentsum=0;
				st=a;
			}
			else{
				currentsum=currentsum+numbers.get(a);
			}
			
			if(currentsum>=ans) {
				ed=a;
			}
			ans=Math.max(ans, currentsum);
			a++;
		}
		logg.info(String.format("Max_Sum:%d",ans));
		numbers.subList(st, ed+1).forEach(i->logg.info(i));

	}

}
