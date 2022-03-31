package othertasks;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@SuppressWarnings("rawtypes")
class Producer implements Callable {

	private static final Logger logg = LogManager.getLogger(Producer.class);
    private final List<Student> data;
    private final int size;
    int productioncount;
    public Producer(List<Student> data, int size,int count) {
        this.data = data;
        this.size = size;
        this.productioncount=count;
    }

    @Override
    public Object call() throws Exception {
    	
    	int i=0;
    	 Scanner sc=new Scanner(System.in);
          while(i<productioncount) {
            try {
            	logg.info("enter id:");
            	int id=sc.nextInt();
            	logg.info("enter name");
            	String name=sc.next();
            	Student student=new Student(id,name);
                logg.info("Produced: " + student.getName());
                produce(student);
                i++;
            } catch (Exception ex) {
                throw new Exception(ex.getMessage());
            }

        }
        return true;
    }

    private void produce(Student i) throws InterruptedException {

        //wait if the queue is full
        while (data.size() == size) {
            synchronized (data) {
                logg.info("The queue is full " + Thread.currentThread().getName()
                                    + " is waiting , size: " + data.size());
                data.wait();
            }
        }

        //producing element and notify consumers
        synchronized (data) {
            data.add(i);
            data.notifyAll();
        }
    }
}



	
