package othertasks;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("rawtypes")
@Slf4j
class Producer implements Callable {

    private final List<Student> data;
    private final int size;

    public Producer(List<Student> data, int size) {
        this.data = data;
        this.size = size;
    }

    @Override
    public Object call() throws InterruptedException {
    	Scanner sc=new Scanner(System.in);
    	log.info("enter production count:");
    	int productioncount=sc.nextInt();
    	int i=0;
          while(i<productioncount) {
        	
        	log.info("enter id:");
        	int id=sc.nextInt();
        	log.info("enter name");
        	String name=sc.next();
        	Student student=new Student(id,name);
            log.info("Produced: " + student.getName());
            try {
                produce(student);
                i++;
            } catch (InterruptedException ex) {
                log.info(ex.getMessage());
            }

        }
        return true;
    }

    private void produce(Student i) throws InterruptedException {

        //wait if the queue is full
        while (data.size() == size) {
            synchronized (data) {
                log.info("The queue is full " + Thread.currentThread().getName()
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



	
