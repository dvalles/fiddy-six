// app.js
// Written by Ryan Brooks

/**
 * Base Setup
 */
var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var port = process.env.PORT || 5000;
var mongoose = require('mongoose');
mongoose.connect('mongodb://localhost/needitDB');
var User = require('./models/userModel');

/**
 * Configurations
*/
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

/**
 * Routes
*/
var router = express.Router();

router.use(function(req, res, next) {
	console.log('Something is happening');
	next();
});

router.get('/', function(req, res) {
	res.json({ message: 'Api working!' });
});

router.route('/users')

	.post(function(req, res) {
		var user = new User;
		user.username = req.body.username;
		user.password = req.body.password;
		user.incorrect_logins = 0;

		console.log('Username: ' + user.username);
		console.log('Password: ' + user.password);

		user.save(function(err) {
			if (err)
				res.send(err);

			res.json({ message: 'User added' });
		});
	})

	.get(function(req, res) {
		User.find(function(err, users) {
			if (err)
				res.send(err);

			res.json(users);
		});
	});

router.route('/users/:user_id')

	.get(function(req, res) {
		User.findById(req.params.user_id, function(err, user) {
			if (err)
				res.send(err);

			res.json(user);
		});
	})

	.put(function(req, res) {
		User.findById(req.params.user_id, function(err, user) {
			if(err)
				res.send(err);

			user.password = req.body.password; // Update password

			user.save(function(err) {
				if (err)
					res.send(err);

				res.json({ message: user.username + ' Updated' });
			});
		});
	})

	.delete(function(req, res) {
		User.remove({
			_id: req.params.user_id
		}, function(err, user) {
			if (err)
				res.send(err);

			res.json({ message: user.username + ' Deleted'});
		});
	});

router.route('/checkUser')

	.post(function(req, res) {
		var user = new User;
		user.username = req.body.username;
		user.password = req.body.password;

		var query = User.findOne({ username: user.username })
						.where('password').equals(user.password);

		query.exec(function(err, user) {
			if (err) {
				res.send(err);
			}

			if (user == null) {
				res.json({ correct: false });
			} else {
				res.json({ correct: true });
			}
			
		});
	});


// Register Routes -- Prefix with /api
app.use('/api', router);

/**
 * Start Server
*/
app.listen(port);
console.log('Server started on port ' + port);