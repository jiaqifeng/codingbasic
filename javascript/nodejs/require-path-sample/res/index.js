'use stric';

var Req=require('../req');

var res = module.exports = function() {
    this.name = 'response';
    this.req = new Req();
}

res.say = function() {
    console.log('res said bye! req.name = ', req.name);
}

console.log('res module end');
