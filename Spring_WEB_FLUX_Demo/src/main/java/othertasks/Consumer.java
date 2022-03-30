package othertasks;

import java.util.List;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("rawtypes")
@Slf4j
class Consumer implements Callable {

    private final List<Integer> data;
    private final int size;

    public Consumer(List<Integer> sharedQueue, int size) {
        this.data = sharedQueue;
        this.size = size;
    }

    public Object call() {
    	int i=0;
        while (i<this.size) {
            try {
                log.info("Consumed: " + consume());
                i++;
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                log.info(Consumer.class.getName());
            }

        }
        return true;
    }

    private int consume() throws InterruptedException {
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
            return (Integer) data.remove(0);
        }
    }
}

