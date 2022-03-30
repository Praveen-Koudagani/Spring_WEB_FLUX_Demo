package othertasks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PCMain {

	    @SuppressWarnings("unchecked")
		public static void main(String[] args) throws InterruptedException, ExecutionException {
	        List<Integer> data = new ArrayList<>();
	        int size = 4;
	        @SuppressWarnings("rawtypes")
			FutureTask producer=new FutureTask(new Producer(data, size));
	        @SuppressWarnings("rawtypes")
			FutureTask consumer=new FutureTask(new Consumer(data, size));
	        Thread prodThread = new Thread(producer, "Producer");
	        Thread consThread = new Thread(consumer, "Consumer");
	        prodThread.start();
	        consThread.start();
	        if(!(boolean) producer.get()) {
	        	log.info("Producer Task Encountered some problem");
	        }
	        else if(!(boolean) consumer.get()) {
	        	log.info("Consumer Task Encountered some problem");
	        }
	        else {
	        	log.info("Producer and Consumer Tasks completed suceessfully...    :) ");
	        }
	    }
	
}
