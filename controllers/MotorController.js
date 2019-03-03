var fs = require('fs');
var path    = require("path");

module.exports = {

	turnOn: function(req, res){

			fs.writeFile(path.join(__dirname+"/../model/motorstatus.json"), '[{"status":"1"}]', function (err) {
			  if (err) throw err;
			  res.end();
			});

	},

	turnOff: function(req, res){

			fs.writeFile(path.join(__dirname+"/../model/motorstatus.json"), '[{"status":"0"}]', function (err) {
			  if (err) throw err;
			  res.end();
			});

	},

	getStatus: function(req, res){

			fs.readFile(path.join(__dirname+"/../model/motorstatus.json"), function(err, data) {
		    res.writeHead(200, {'Content-Type': 'application/json'});
		    res.end(data);
		    res.end();
		  	});
	}

};