var url = 'http://myurl.io.com';

function log(message){
    //Send a http request
    console.log(message);
}

module.exports.log = log; // export entire file

module.exports = log; // export one function

module.exports.endpoint = url;


const EventEmitter = require('events');

class Logger extends EventEmitter{
    log(message){
        console.log(message);

        this.emit('message', message);
    }
}
module.exports = Logger;