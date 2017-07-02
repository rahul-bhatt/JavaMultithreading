package creatingthreads;

/**
 * @author rahulbhatt
 * Using Runnable is more preferred way to create threads
 * because the Runnable object can subclass a class other than Thread.
 * 
 * Not only is this approach more flexible, but it is applicable to the high-level thread management APIs
 */
public class HelloRunnable implements Runnable {
	public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new Thread(new HelloRunnable())).start();
    }
}
