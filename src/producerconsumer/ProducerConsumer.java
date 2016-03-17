package producerconsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


/**
 * Solution to Producer Consumer problem using wait and notify.
 * @author Rahul.B
 */
public class ProducerConsumer {

	public static void main(String args[]) {
		Queue<Integer> sharedQueue = new LinkedList<Integer>();
		int maxSize = 10;

		Thread prodThread = new Thread(new Producer(sharedQueue, maxSize), "Producer");
		Thread consThread = new Thread(new Consumer(sharedQueue, maxSize), "Consumer");

		prodThread.start();
		consThread.start();
	}
}

/*
 * If queue is full wait, else
 * add to queue and notify consumer thread.
 */
class Producer implements Runnable {

	private final Queue<Integer> sharedQueue;
	private final int maxSize;

	public Producer(Queue<Integer> sharedQueue, int size) {
		this.sharedQueue = sharedQueue;
		this.maxSize = size;
	}

	@Override
	public void run() {
		while (true) { 
			synchronized (sharedQueue) { 
				while (sharedQueue.size() == maxSize) { 
					try { 
						System.out .println("Queue is full, " + "Producer thread waiting for " + 
					"consumer to take something from queue"); 
						
						sharedQueue.wait(); 
					} catch (Exception ex) { 
						ex.printStackTrace(); 
					} 
				} 
				
				Random random = new Random(); 
				int i = random.nextInt(); 
				System.out.println("Producing value : " + i); 
				sharedQueue.add(i); 
				sharedQueue.notifyAll(); 
			} 
		}
	}

}

/*
 * wait if queue is empty, else
 * consume from queue and notify producer thread.
 */
class Consumer implements Runnable {

	private final Queue<Integer> sharedQueue;
	private final int maxSize;

	public Consumer(Queue<Integer> sharedQueue, int size) {
		this.sharedQueue = sharedQueue;
		this.maxSize = size;
	}

	@Override
	public void run() {
		synchronized (sharedQueue) { 
			while (sharedQueue.isEmpty()) { 
				System.out.println("Queue is empty," + "Consumer thread is waiting" + 
						" for producer thread to put something in queue"); 
				
				try { 
					sharedQueue.wait(); 
				} catch (Exception ex) { 
					ex.printStackTrace(); 
				} 
			} 
			
			System.out.println("Consuming value : " + sharedQueue.remove()); 
			sharedQueue.notifyAll(); 
		} 
	}
}