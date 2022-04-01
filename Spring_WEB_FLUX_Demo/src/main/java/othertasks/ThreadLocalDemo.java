package othertasks;


public class ThreadLocalDemo {

	public static void main(String[] args) throws InterruptedException {
        MyThread sharedRunnable= new MyThread();

        Thread thread1 = new Thread(sharedRunnable);
        Thread thread2 = new Thread(sharedRunnable);

        thread1.setPriority(10);
        thread2.setPriority(1);
        thread1.start();
        thread2.start();
        thread1.join(); 
        thread2.join();
   
       
    }

}
