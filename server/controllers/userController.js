
var userController = function() {};

userController.prototype.signUp = function() {
	console.log("Yo, you loggin stuff.");
};

module.exports = new userController();