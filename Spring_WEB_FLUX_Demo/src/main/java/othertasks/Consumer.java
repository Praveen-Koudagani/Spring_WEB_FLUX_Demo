package othertasks;

import java.util.List;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("rawtypes")
@Slf4j
class Consumer implements Callable {

    private final List<Student> data;

    public Consumer(List<Student> data) {
        this.data = data;
    }

    public Object call() {
        while (!this.data.isEmpty()) {
            try {
            	Thread.sleep(20000);
            	Student st=consume();
                log.info("Consumed: " + st.getId()+"  "+st.getName());
                
            } catch (InterruptedException ex) {
                log.info(ex.getMessage());
            }

        }
        return true;
    }

    private Student consume() throws InterruptedException {
        //wait if the queue is empty
        while (data.isEmpty()) {
            synchronized (data) {
                log.info("The queue is empty " + Thread.currentThread().getName()
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

