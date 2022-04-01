package othertasks;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyThread implements Runnable {

	private static final Logger logg = LogManager.getLogger(Consumer.class);
    private ThreadLocal<Student> threadLocal = new ThreadLocal<Student>() {
    	protected Student initialValue() {
    		return new Student(0,"admin");
    	};
    };

    @Override
    public void run() {
        try {
        	 Thread.sleep(4000);
        	Scanner sc=new Scanner(System.in);
        	logg.info("enter option as 1 for changing threadlocal value else other number "+Thread.currentThread());
        	int option=sc.nextInt();
        	if(option==1) {
            threadLocal.set(new Student(1,"praveen"));
           
        	}
        } catch (InterruptedException e) {
        	logg.info(e.getMessage());
        }

        logg.info(threadLocal.get());
    }
}