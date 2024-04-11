package simple;
import java.util.LinkedList;

/**
 * Acts as the queue
 * */
public class Buffer {
	
	//constructor with buffer size
	public Buffer(int _size) {
		//Set initial buffer size
		bufferSize = _size;
	}
	
	//buffer/queue size
	private int bufferSize;
	//Use LinkedList to preserve order of occurrence i.e. fifo or lifo
	private LinkedList<String> queue = new LinkedList<String>();
	private LinkedList<String> errors = new LinkedList<String>();
	
	/**
	 * Writes transaction to queue and if queue is full, emits an error into errors.
	 * */
	public void sendTrxnToQueue(String transaction) {
			
		if(queue.size()< bufferSize) {
			System.out.println("Info: Buffer: "+transaction);
			queue.add(transaction);
		}else {
			System.err.println("Warn: Buffer full - skipped trxn: "+transaction);
			errors.add("Buffer full - skipped trxn: "+transaction);
			
		}
				
	}
	
	/**
	 * Reads from first item in queue and dequeues the transaction else returns null.
	 * */
	public String readTxnFromQueue() {
		
		if(queue.size()>0) {
			return queue.removeFirst();
		}else {
			return null;
		}
		
	}
	
	/**Prints out all errors and emties the error buffer */
	public void readErrors() {
		
		while(true) {
			
			if(errors.isEmpty()) {
				break;
			}
			
			System.out.println(errors.removeFirst());
		}
		
	}
	
	

}
