
let queue = [];
let errors = [];
 
const { WebSocketServer } = require('ws')
const sockserver = new WebSocketServer({ port: 8777, path: '/write' })
sockserver.on('connection', ws => {
 console.log('New Write client connected!')
 ws.send('Write connection established')
 ws.on('close', () => console.log('Write Client has disconnected!'))
 ws.on('message', data => {
   sockserver.clients.forEach(client => {
     console.log(`Write distributing message: ${data}`)
     if(queue.length < 10 ){
		queue.push(data);
		client.send(`Success`);
	 }else{
		errors.push(data);
		client.send(`Failed due to filled cache`);
	 }
     
   })
 })
 ws.onerror = function () {
   console.log('websocket error')
 }
})


const sockserver2 = new WebSocketServer({ port: 8778, path: '/read' })
sockserver2.on('connection', ws => {
 console.log('New Read client connected!')
 ws.send('Read connection established')
 ws.on('close', () => console.log('Read Client has disconnected!'))
 ws.on('message', data => {
   sockserver2.clients.forEach(client => {
     //console.log(`distributing message: ${data}`)
     //client.send(`${data}`)
     if(queue.length>0){
		client.send(queue.shift());
	 }else{
		client.send(null)
	}

   })
 })
 ws.onerror = function () {
   console.log('Read websocket error')
 }
})