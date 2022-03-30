package othertasks;

import java.util.List;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("rawtypes")
@Slf4j
class Producer implements Callable {

    private final List<Integer> data;
    private final int size;

    public Producer(List<Integer> sharedQueue, int size) {
        this.data = sharedQueue;
        this.size = size;
    }

    @Override
    public Object call() {
        for (int i = 0; i < 7; i++) {
            log.info("Produced: " + i);
            try {
                produce(i);
            } catch (InterruptedException ex) {
                log.info(Producer.class.getName());
            }

        }
        return true;
    }

    private void produce(int i) throws InterruptedException {

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



	
