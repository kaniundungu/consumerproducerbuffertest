package simple;


/**
 * The consuming service 
 * */
public class Consumer {
	
	/** 
	 * Requires buffer object and timing to check buffer.
	 * */
	public void start(Buffer buff, int millisecs) {
		
		System.out.println("Consumer started ");	
		
		//run forever
		while(true) {
			try {
			
				Thread.sleep(millisecs);
				String transaction = buff.readTxnFromQueue();
				if( validateData(transaction) )
					//success
					System.out.println("Info: Consumer : "+transaction);
				else {
					System.err.println("Warn: Consumer : null transaction, buffer is empty ");
				}
			
			}catch(Exception e) {
				
				System.err.printf("Error: Consumer  : error %s \n ",e);
			}
		}
		
	}
	
	
	/**
	 * Validates a transaction exists and throws error if format is incorrect.
	 * */
	private boolean validateData(String dat) throws Exception {
		
		if(dat==null || dat.isEmpty()) {
			return false;
		}else {
			//perform data format check
			if(dat.split(",").length != 3) throw new Exception("Data format error: expected type,amount,id ");
			//add other checks such as data type
		}
		
		return true;
	}

}
