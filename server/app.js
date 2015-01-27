/*jslint node: true */

/**
 * Dependencies
*/
var bodyParser = require('body-parser');
var express = require('express');
var mongoose = require('mongoose');
var path = require('path');
var userController = require('./userController')

var app = express();

/**
 * Express configs
*/

app.set('port', 3000);
app.use(express.static(path.join(__dirname, 'public')));
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json({ limit: '900mb'}));

var router = express.Router();
//var userController = function() {};

// userController.prototype.signUp = function() {
// 	console.log("Yo, you loggin stuff.");
// };

//var userObj = new userController();

router.get('/user/get', function(req, res) {
	console.log(res);
	console.log('This is a user');
	res.json({
		status: true,
		message: 'This is a user'
	});
});

router.get('/collection/get', userController.signUp);

router.post('/user/signup', function(req, res) {
	
	console.log('This is a collection');
	res.json({
		status: true,
		message: 'This is a signup'
	});
});


app.use('/api', router);

app.listen(app.get('port'), function() {
	console.log('Express listening to port', app.get('port'));
});

module.exports = app;