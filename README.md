
## ConsumerProducerzBufferTest

This is an example of a consumer and producer interaction with buffering/queueing.

#### Running the example

 - Assumptions: That you have a jdk installed in your environment.
 
 - Clone the project 
 
	```
	 git clone https://github.com/kaniundungu/consumerproducerbuffertest.git
	 
	 ```
 
 - Change dir to app and compile
 
    ```
    	 cd consumerproducerbuffertest
    	 mkdir bin/
    	 javac -d bin/ src/simple/*.java 
    ```
    
- Execute the main class
	
	```
	java -cp bin/ simple.SimpleTest
	```    
- Executing with custom configuration values i.e. PRODUCER_INTERVALS, CONSUMER_INTERVALS, BUFFER_QUEUE_SIZE, MAX_PRODUCT_PRICE

	```
	java -DPRODUCER_INTERVALS=1000 -DCONSUMER_INTERVALS=2000 -DBUFFER_QUEUE_SIZE=10 -DMAX_PRODUCT_PRICE=99 -cp bin/ simple.SimpleTest
	```  
	
	