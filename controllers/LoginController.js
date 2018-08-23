var db = require('../model/connect');

module.exports = {

	login: function (req,res){

		var sql = `SELECT * FROM users WHERE u_mobileno = "${req.query.u_no}"`;
	  	db.query(sql, function (err, rows, fields) {

	  		console.log(sql);

		    if (err) throw err;
		    
		    res.writeHead(200, { 'Content-Type': 'application/json'});
		    res.end(JSON.stringify(rows));
		    res.end();

		});

	}

};