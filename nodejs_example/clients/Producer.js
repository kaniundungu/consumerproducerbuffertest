
'use strict';

//import dependencies
const uuid = require('uuid');
const WebSocketClient = require('websocket').client;
const crypto = require('crypto');

//configure work constants
const products = [ "apple","banana","strawberry","grape","pineapple"]
const maxprice = 10;
const ping_interval = 12000;

//instantiate object
var client = new WebSocketClient();
let interval;


client.on('connectFailed', function(error) {
    console.log('Connect Error: ' + error.toString());
});

client.on('connect', function(connection) {
    console.log('Connection established!');
    interval = setInterval(() => {
		let pIndex = crypto.randomInt(products.length-1);
    	let data = products[pIndex]+','+crypto.randomInt(maxprice)+','+crypto.randomUUID();
		console.log('writing : %s', data);
    	connection.send(data);
  	}, ping_interval);
  	
  
    
    connection.on('error', function(error) {
        console.log("Connection error: " + error.toString());
    });
    
    connection.on('close', function() {
        console.log('Connection closed!');
        clearInterval(interval);
    });
    
    connection.on('message', function(message) {
        console.log("Current time on server is: '" + message.utf8Data + "'");
    });
});

client.connect('ws://localhost:8777/write');