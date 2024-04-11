package simple;

import java.util.Random;
import java.util.UUID;


public class Producer {
	
	//product types preset
	private static final String [] type = {"apple","banana","strawberry","grape","pineapple"};
	
	//Maximum price the can be generated for a product
	private int maxPrice = 10;
	
	public Producer(int _maxPrice){
		maxPrice = _maxPrice;
	}
	/**
	 * Begin random transactions.
	 * */
	public void start(Buffer buff, int milliSecs) {
		
		System.out.println("Info: Producer started: Transaction format = type,amount,id");
		
		Random rand = new Random();
		
		String transaction = "";
		UUID uid ;
		int amount ;
		int typeindex;
		
		do {
			
			amount = rand.nextInt(maxPrice);
			typeindex = (int)rand.nextInt(type.length-1);
			uid = UUID.randomUUID();
			
			try {
				
				transaction = type[typeindex]+","+amount+","+uid.toString();
				System.out.println("Info: Producer: "+transaction);
				
				buff.sendTrxnToQueue(transaction);
				
				Thread.sleep(milliSecs);
			
			}catch(Exception e) {
				
				System.err.printf("Error: Producer  : error %s \n ",e.getMessage());
				
			}
			
		}while(true);
			
	}

}
