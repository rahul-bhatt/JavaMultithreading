package creatingthreads;

/**
 * @author rahulbhatt
 * Creating thread using the Thread class is easier to use in simple applications, 
 * but is limited by the fact that your task class must be a descendant of Thread.
 * 
 * More preferred way is using Runnable
 */
public class HelloThread extends Thread {
	public void run() {
        System.out.println("Hello from a thread!");
    }

    public static void main(String args[]) {
        (new HelloThread()).start();
    }
}
