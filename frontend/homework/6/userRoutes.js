const express = require('express');
const router = express.Router();
const userController = require('./userController');

// Route to create a new user
router.post('/', userController.createUser);

// Route to get all users
router.get('/', userController.getAllUsers);

// Route to find user by ID
router.get('/:id', userController.findUserById);

module.exports = router;
