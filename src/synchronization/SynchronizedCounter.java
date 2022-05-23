package synchronization;

/**
 * 
 * If count is an instance of SynchronizedCounter, then making these methods synchronized has two effects:
 * 
 * First, it is not possible for two invocations of synchronized methods on the same object to interleave. 
 * When one thread is executing a synchronized method for an object, all other threads that invoke synchronized 
 * methods for the same object block (suspend execution) until the first thread is done with the object.
 * 
 * Second, when a synchronized method exits, it automatically establishes a happens-before relationship with 
 * any subsequent invocation of a synchronized method for the same object. 
 * This guarantees that changes to the state of the object are visible to all threads.
 * 
 * @author rahulbhatt
 *
 */
public class SynchronizedCounter implements Runnable {

	public static void main(String[] args) {
		SynchronizedCounter sc = new SynchronizedCounter();
		Thread t1 = new Thread(sc);
		Thread t2 = new Thread(sc);
		t1.start();
		t2.start();
	}

	private int c = 0;

    public synchronized void increment() {
    	System.out.println(Thread.currentThread().getName() + " : incrementing c");
        c++;
    }

    public synchronized void decrement() {
    	System.out.println(Thread.currentThread().getName() + " : decrementing c");
        c--;
    }

    public synchronized int value() {
    	System.out.println(Thread.currentThread().getName() + " : reading c");
        return c;
    }

	@Override
	public void run() {
		increment();
		decrement();
		System.out.println(Thread.currentThread().getName() + " : c value = " + value());
	}
}
