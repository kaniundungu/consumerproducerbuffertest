# Websockets Consumer Producer Cache Example 

Demonstrate a simple cache service to queue data.
This a nodejs implementation.

### Running the sample

 - Clone the project 
 
	```
	 git clone https://github.com/kaniundungu/consumerproducerbuffertest.git
	 
	 ```
	 
- Change dir to nodejs examples and install dependencies. Also start the cache service.


	```
	 cd consumerproducerbuffertest
	 
	 cd nodejs_example/cacheService
	 
	 npm update
	 
	 node wsApp.js
	 
	```
	
	
- From project root, start another terminal and install client dependencies. Start the consumer client.
	
	````
	 cd nodejs_example/clients
	 
	 npm update
	 
	 node Consumer.js
	````
	 
- From project root, start another terminal and install client dependencies (if not already done above). Start the Producer client.
	
	````
	 cd nodejs_example/clients
	 
	 #optional
	 npm update
	 
	 node Producer.js
	````
	
	
