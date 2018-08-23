var mysql = require('mysql');
var connection = mysql.createConnection({
    host     : '127.0.0.1',
    user     : 'root',
    password : '',
    database : 'crop_watch'
});

connection.connect(function(err) {
    if (err) throw err;
});

module.exports = connection;