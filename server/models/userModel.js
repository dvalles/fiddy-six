// server/models/userModel.js
// Written by Ryan Brooks
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var UserSchema = new Schema({
	username: String,
	password: String,
	incorrect_logins: Number
});

module.exports = mongoose.model('User', UserSchema);