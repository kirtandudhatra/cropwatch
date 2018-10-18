var db = require('../model/connect');

module.exports = {

	login: function (req,res){

		var sql = `SELECT * FROM users WHERE u_email = "${req.query.email}" AND u_pass = "${req.query.pass}" AND u_role = 0`;
	  	db.query(sql, function (err, rows, fields) {

	  		console.log(sql);

		    if (err) throw err;
		    
		    res.writeHead(301,{Location: 'admindashboard'});
			res.end();

		});

	},

	loginView: function(req, res){


	}

};