// add () at the tail of below line return different function
var Chart=require('./chart')();
console.log("before new Chart, Chart type ",typeof Chart);
console.log("chart.name=", Chart.name, ',', Chart.toString());
var c=new Chart("hello");
var sum=function(a, b) { return a + b; }
console.log("sum is", typeof sum);
console.log("sum.prototype=", sum.prototype.toString());
var o = { name: "Jack" };
function speak() { console.log(this.name);}
o.speak = speak;
o.speak();
