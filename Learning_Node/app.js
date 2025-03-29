// No window in Node, only global
// We can only access standard window properties with global
// No more variables can be aaded to global

var message = " ";
console.log(global.message); // undefined


// in Node, every file is a module
// every variables and functions defined in that module is scoped in that module
// refer to logger.js
console.log(module);

// Load the module
const logger = require('./logger');// avoiding overwritting(const)

logger.log('message');

const log = require('./logger');
log('message');// directly call the function

//Path module
const path = require('./path');

var pathObj = path.parse(_filename);
console.log(pathObj);

//Operating system module
const os = require('os');

var totalMemory = os.totalMemory();
var freeMemory = os.freeMemory();

console.log('Total memory:' + totalMemory);

// File system module
const fs = require('fs');
const files = fs.readdirSync('./');
console.log(files);

fs.readdir('./', function(err, files){
    if (err) console.log('Error', err);
    else console.log('Result', files);
});

// Event module
const EventEmitter = require('events'); // Event captital means this is a class not a function
const emitter = new EventEmitter(); // an object

// Register a listener
emitter.on('messgeLogged', function(arg){//e, eventArg
    console.log('Listener registered', arg); 
});
// Arrow function
emitter.on('messgeLogged', (arg) =>{//e, eventArg
    console.log('Listener registered', arg); 
});


// raise an event
emitter.emit('messageLogged', {id: 1, url:'http://localhost'});

//listener is a function that will be called when that event is emitted


// emitter is an object, so different emitter(in different files) is unable to be listened by the same listener
// so creat a class that has all the properties of emitter
// Load Logger class
const Logger = require('./logger');
const logger = new Logger();// create a object
logger.log('message');

//http module
const http = require('http');

//const server = http.createServer();

server.on('connection', (socket)=> {
    console.log('New connection');
}) 
// another way
const server = http.createServer((req,res) => {
    if (req.url === '/') {
        res.write('hello world');
        res.end();
    }

    if (req.url === '/api/courses'){
        res.write(JSON.stringify([1, 2, 3]));// convert this array to a string
    }
   
});

server.listen(3000);
console.log("Listening on port 3000...");
