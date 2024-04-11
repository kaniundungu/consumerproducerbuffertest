package simple;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * This is the executor class for this example.
 * */
public class SimpleTest {
	
	
	public static int PRODUCER_INTERVALS = 3000;
	public static int CONSUMER_INTERVALS = 1000;
	public static int BUFFER_QUEUE_SIZE = 10;
	public static int MAX_PRODUCT_PRICE = 20;
	
	public static void main(String[] args) {
		
		//These can be user defined properties for IO intervals and buffer size or product price
		PRODUCER_INTERVALS = Integer.parseInt(System.getProperty("PRODUCER_INTERVALS",""+PRODUCER_INTERVALS));
		CONSUMER_INTERVALS = Integer.parseInt(System.getProperty("CONSUMER_INTERVALS",""+CONSUMER_INTERVALS));
		BUFFER_QUEUE_SIZE = Integer.parseInt(System.getProperty("BUFFER_QUEUE_SIZE",""+BUFFER_QUEUE_SIZE));
		MAX_PRODUCT_PRICE = Integer.parseInt(System.getProperty("MAX_PRODUCT_PRICE",""+MAX_PRODUCT_PRICE));

		System.out.println("PRODUCER_INTERVALS ="+PRODUCER_INTERVALS);
		System.out.println("CONSUMER_INTERVALS ="+CONSUMER_INTERVALS);
		System.out.println("BUFFER_QUEUE_SIZE ="+BUFFER_QUEUE_SIZE);
		System.out.println("MAX_PRODUCT_PRICE ="+MAX_PRODUCT_PRICE);
		
		//initialize all services
		Producer prod = new Producer(MAX_PRODUCT_PRICE);
		Consumer consumer = new Consumer();
		Buffer buff = new Buffer(BUFFER_QUEUE_SIZE);
		
		
		// producer service thread
		Runnable prodThread = new Runnable() {

			@Override
			public void run() {
				prod.start(buff, PRODUCER_INTERVALS);
			}
			
			
		};
	
		
		// consumer service thread
		Runnable consumerThread = new Runnable() {

			@Override
			public void run() {
				consumer.start(buff, CONSUMER_INTERVALS);
		}};
			
		
		//start the consumer and producer services independent of each other
		Executor exec = Executors.newFixedThreadPool(2);
		exec.execute(consumerThread);
		exec.execute(prodThread);
			
		
		
	}
	
	

}
