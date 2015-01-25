/*jslint node: true */

/**
 * Dependencies
*/
var bodyParser = require('body-parser');
var express = require('express');
var mongoose = require('mongoose');
var path = require('path');

var app = express();

/**
 * Express configs
*/

app.set('port', 3000);
app.use(express.static(path.join(__dirname, 'public')));
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json({ limit: '900mb'}));

var router = express.Router();

router.get('/user/get', function(req, res) {
	console.log(res);
	console.log('This is a user');
	res.json({
		status: true,
		message: 'This is a user'
	});
});

router.get('/collection/get', function(req, res) {
	console.log('This is a collection');
	res.json({
		status: true,
		message: 'This is a collection'
	});
});

app.use('/', router);

app.listen(app.get('port'), function() {
	console.log('Express listening to port', app.get('port'));
});

module.exports = app;