// test vm.runInThisContext can wait for async handler.
// result is: it just run the code wont wait setTimeout callback

// timeout-sayhello.js code as below
// console.log('timeout-sayhello script start ...');
// setTimeout(function() {
//     console.log('hello world!');
// }, 2000);
// console.log('timeout-sayhello script end ...');


"strict";

var vm = require('vm');
var fs = require('fs');

var filePath = 'timeout-sayhello.js';
try {
    var content = fs.readFileSync(filePath);
} catch (e) {
    console.error(e);
    return {};
}

//var code = '(' + content + ')';
var code = content;
var sandbox = {};
console.log('start vm.runInThisContext ...');
vm.runInThisContext(code, sandbox, {
    filename: filePath,
    displayErrors: false,
    timeout: 10000
});
console.log('end vm.runInThisContext ...');
