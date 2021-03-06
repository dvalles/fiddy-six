// app.js
// Written by Ryan Brooks

// Setup
var express = require('express');
var app = express();
var config = require('./config/secrets').config();

var bodyParser = require('body-parser');
var mongoose = require('mongoose');
var passport = require('passport');

var User = require('./models/userModel');
var UserController = require('./controllers/UserController');
var userController = new UserController(User);

// Configs
app.set('port', config.port);
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use(passport.initialize());

// Routes
var router = express.Router();

router.get('/', function(req, res) {
	res.json({ message: 'Api working!' });
});

// Auth
router.post('/user/signup', userController.signup);
router.post('/user/login', userController.login);
// Following
router.post('/user/follow', userController.follow);
router.post('/user/un-follow', userController.unFollow);
router.post('/user/add-follower', userController.addFollower);
router.post('/user/remove-follower', userController.removeFollower);
router.get('/user/get-followers', userController.getFollowers);
router.get('/user/get-following', userController.getFollowing);
router.get('/get-all-users', userController.getAllUsers);
// DB
var db = mongoose.connect(config.mongoDB, function(err) {
	if (err) { console.error('Could not establish connection with MongoDB' + err); }
});

// Register Routes -- Prefix with /api
app.use('/api', router);

// Server
app.listen(app.get('port'), function() {
	console.log('Express listening to port', app.get('port'));
});

