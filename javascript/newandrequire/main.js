// add () at the tail of below line return different function
var Chart=require('./chart')();
console.log("before new Chart, Chart type ",typeof Chart);
console.log("chart.name=", Chart.name, ',', Chart.toString());
var c=new Chart("hello");
