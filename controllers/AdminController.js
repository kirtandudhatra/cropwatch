var db = require('../model/connect');

module.exports = {

		addUser: function (req,res){

			var sql = `INSERT INTO users (u_name,u_email,u_pass,u_mobileno,u_address,u_role) VALUES ('${req.query.uname}','${req.query.email}','${req.query.pass}','${req.query.num}','${req.query.add}',1)`;
		  	
			db.query(sql, function (err, result) {
			    
			    if (err) throw err;

			    res.write("<script>alert('User Added!!');window.location.replace('/admindashboard');</script>");
			    
				res.end();
			  
		});

		},

		getUsers: function (req,res){

				var sql = `SELECT * FROM users`;
	  			db.query(sql, function (err, rows, fields) {

			    if (err) throw err;
			    
			    res.writeHead(200, { 'Content-Type': 'application/json'});
			    res.end(JSON.stringify(rows));
			    res.end();

		});
		}

};