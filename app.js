var express = require('express');
var app = express();
var logindata = require('./controllers/LoginController');
var sensordata = require('./controllers/SensorDataController');

app.get('/send',function(req,res){
	
	sensordata.insertData(req,res);
	
});


app.get('/fetch', function(req,res){

	sensordata.fetchData(req,res);
});

app.get('/login', function(req,res){

	logindata.login(req,res);
});

app.listen(3000);

console.log("Express app running on port 3000");

module.exports = app;	