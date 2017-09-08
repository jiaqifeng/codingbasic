var Request = require('sync-request');
var req = Request('GET', "http://api.config.zhubajie.la/conf/get?module=nodejs-config-client&profile=dev&version=default");
console.log(JSON.parse(req.getBody('utf-8')));
