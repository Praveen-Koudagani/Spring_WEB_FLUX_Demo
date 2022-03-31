package othertasks;

import java.util.List;
import java.util.concurrent.Callable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("rawtypes")
class Consumer implements Callable {

	private static final Logger logg = LogManager.getLogger(Consumer.class);
    private final List<Student> data;
    private int productioncount;

    public Consumer(List<Student> data,int count) {
        this.data = data;
        this.productioncount=count;
    }

    public Object call() {
        int i=0;
		while(i<productioncount) {
            try {
            	Thread.sleep(10000);
            	Student st=consume();
                logg.info("Consumed: " + st.getId()+"  "+st.getName());
                i++;
                
            } catch (InterruptedException ex) {
                logg.info(ex.getMessage());
            }

        }
        return true;
    }

    private Student consume() throws InterruptedException {
        //wait if the queue is empty
        while (data.isEmpty()) {
            synchronized (data) {
                logg.info("The queue is empty " + Thread.currentThread().getName()
                                    + " is waiting , size: " + data.size());

                data.wait();
            }
        }

        //Otherwise consume element and notify the waiting producer
        synchronized (data) {
            data.notifyAll();
            return  data.remove(0);
        }
    }
}

