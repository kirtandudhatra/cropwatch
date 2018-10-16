var mysql = require('mysql');
var connection = mysql.createConnection({
    host     : '192.168.64.2',
    user     : 'root',
    password : '',
    database : 'crop_watch'
});

connection.connect(function(err) {
    if (err) throw err;
});

module.exports = connection;