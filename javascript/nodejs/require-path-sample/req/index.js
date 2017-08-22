'use stric';

var req = module.exports = function() {
    this.name='request';
}

req.say = function () {
    console.log("req said cool");
}

console.log('req module end');
