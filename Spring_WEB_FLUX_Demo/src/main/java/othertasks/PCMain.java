package othertasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class PCMain {

	private static final Logger logg = LogManager.getLogger(PCMain.class);
	    @SuppressWarnings("unchecked")
		public static void main(String[] args) throws Exception {
	        List<Student> data = new ArrayList<>();
	        Scanner sc=new Scanner(System.in);
	    	logg.info("enter production count:");
	    	int productioncount=sc.nextInt();
	        int size = 2;
	        @SuppressWarnings("rawtypes")
			FutureTask producer=new FutureTask(new Producer(data, size,productioncount));
	        @SuppressWarnings("rawtypes")
			FutureTask consumer=new FutureTask(new Consumer(data,productioncount));
	        Thread prodThread = new Thread(producer, "Producer");
	        Thread consThread = new Thread(consumer, "Consumer");
	        prodThread.start();
	        consThread.start();
	        if(!(boolean) producer.get()) {
	        	logg.info("Producer Task Encountered some problem");
	        }
	        else if(!(boolean) consumer.get()) {
	        	logg.info("Consumer Task Encountered some problem");
	        }
	        else {
	        	logg.info("Producer and Consumer Tasks completed suceessfully...    :) ");
	        }
	    }
	
}
