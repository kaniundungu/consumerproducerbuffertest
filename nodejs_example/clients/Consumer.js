
'use strict';

const WebSocketClient = require('websocket').client;

var client = new WebSocketClient();
const ping_interval = 12000;
let interval;

client.on('connectFailed', function(error) {
    console.log('Connect Error: ' + error.toString());
});

client.on('connect', function(connection) {
    console.log('Connection established!');
    //trigger a read by sending something
    interval = setInterval(() => {
    	let data = 'kn requesting something';
		console.log('sending request : %s', data);
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
        if(message.binaryData)
         console.log("Data : '" + String.fromCharCode(...message.binaryData) + "'");
    });
});

client.connect('ws://localhost:8778/read');