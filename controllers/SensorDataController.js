var db = require('../model/connect');

module.exports = {
	
	insertData: function (req,res){

		var sql = `INSERT INTO data (u_id,humidity_data,moisture_data,temperature_data) VALUES (${req.query.u_id},${req.query.h},${req.query.m},${req.query.t})`;
		  	
		db.query(sql, function (err, result) {
		    
		    if (err) throw err;
		  
		});
	},

	fetchData: function (req,res){

		var sql = `SELECT * FROM data ORDER BY data_id DESC LIMIT 10`;
	  	db.query(sql, function (err, rows, fields) {

		    if (err) throw err;
		    
		    res.writeHead(200, { 'Content-Type': 'application/json'});
		    res.end(JSON.stringify(rows));
		    res.end();

		});

	}



};