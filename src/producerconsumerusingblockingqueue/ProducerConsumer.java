/**
 * 
 */
package producerconsumerusingblockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * ProducerConsumer solution using BlockingQueue
 * @author Rahul
 * @param <E>
 *
 */
public class ProducerConsumer<E> {
	BlockingQueue<E> sharedQueue = new LinkedBlockingDeque<E>();

	public static void main(String args[]) {
		ProducerConsumer<Integer> prodCons = new ProducerConsumer<Integer>();
		prodCons.execute();
	}
	
	public void execute() {
		Thread prodThread = new Thread(new Producer<>(sharedQueue));
		Thread consThread = new Thread(new Consumer<>(sharedQueue));
		
		prodThread.start();
		consThread.start();
	}
}

class Producer<E> implements Runnable {

	private final BlockingQueue<E> sharedQueue;
	
	public Producer(BlockingQueue<E> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}
	
	@Override
	public void run() {
		for(Integer i = 0; i < 10; i++) {
			try {
				System.out.println("Producer adding: " + i);
				sharedQueue.put((E) i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Consumer<E> implements Runnable {

	private final BlockingQueue<E> sharedQueue;
	
	public Consumer(BlockingQueue<E> sharedQueue) {
		this.sharedQueue = sharedQueue;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				System.out.println("Consumer removing: " + sharedQueue.take());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
