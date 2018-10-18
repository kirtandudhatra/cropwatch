var express = require('express');
var path    = require("path");
var app = express();
var logindata = require('./controllers/LoginController');
var sensordata = require('./controllers/SensorDataController');
var admindata = require('./controllers/AdminController');
var complain = require('./controllers/ComplainController');
var feedback = require('./controllers/FeedbackController');


app.get('/admin',function(req,res){
	
	res.sendFile(path.join(__dirname+"/view/login.html"));
	 
});
app.get('/admindashboard',function(req,res){
	res.sendFile(path.join(__dirname+"/view/dashboard.html"));
	 
});
app.get('/createuser',function(req,res){
	res.sendFile(path.join(__dirname+"/view/createuser.html"));
	 
});
app.get('/adduser',function(req,res){
	
	admindata.addUser(req,res);
	 
});

app.get('/send',function(req,res){
	
	sensordata.insertData(req,res);
	 
});


app.get('/fetch', function(req,res){

	sensordata.fetchData(req,res);
});

app.get('/login', function(req,res){

	logindata.login(req,res);
});

app.get('/complain', function(req,res){

	complain.insertData(req,res);
});

app.get('/feedback', function(req,res){

	feedback.insertData(req,res);
});

app.listen(3000,'0.0.0.0');

console.log("Express app running on port 3000");

module.exports = app;	