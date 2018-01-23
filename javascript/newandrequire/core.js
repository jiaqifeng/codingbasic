'use strict';

module.exports = function() {
    var Chart = function(item, config) {
	console.log("core.js:Chart(): in here");
    }
    Chart.Chart=Chart;
    return Chart;
}
