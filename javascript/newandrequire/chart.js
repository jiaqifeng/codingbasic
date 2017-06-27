
console.log("enter chart.js");
//var Chart = require('core.js')();
module.exports = function(name2) {
    console.log("enter chart.js:window.chart");
    var Chart = function(name) {
	this.name=name;
	console.log("core.js:Chart(): in here");
    };
    Chart.Chart=Chart;
    return Chart;
};
console.log("leave chart.js");
