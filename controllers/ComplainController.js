var db = require('../model/connect');

module.exports = {
	
	insertData: function (req,res){

		var sql = `INSERT INTO complain (c_uid,c_title,c_body) VALUES (${req.query.u_id},${req.query.t},${req.query.b})`;
		  	
		db.query(sql, function (err, result) {
		    
		    if (err) throw err;
		    res.end();
		  
		});
	},

	fetchData: function (req,res){

		var sql = `SELECT * FROM complain INNER JOIN users ON complain.c_uid = users.u_id`;
	  	db.query(sql, function (err, rows, fields) {

		    if (err) throw err;
		    
		    res.writeHead(200, { 'Content-Type': 'application/json'});
		    res.end(JSON.stringify(rows));
		    res.end();

		});

	}



};