var db = require('../model/connect');

module.exports = {
	
	insertData: function (req,res){

		var sql = `INSERT INTO feedback (f_uid,f_title,f_body) VALUES (${req.query.u_id},${req.query.t},${req.query.b})`;
		  	
		db.query(sql, function (err, result) {
		    
		    if (err) throw err;
		    res.end();
		  
		}); 
	},

	fetchData: function (req,res){

		var sql = `SELECT * FROM feedback INNER JOIN users ON feedback.f_uid = users.u_id`;
	  	db.query(sql, function (err, rows, fields) {

		    if (err) throw err;
		    
		    res.writeHead(200, { 'Content-Type': 'application/json'});
		    res.end(JSON.stringify(rows));
		    res.end();

		});

	}



};