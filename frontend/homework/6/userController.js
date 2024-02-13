const { v4: uuidv4 } = require('uuid');
let users = [];

// Controller function to create a new user
function createUser(req, res) {
    try {
        // Validating input
        if (!req.body.username || !req.body.password) {
            throw new Error('Username and password are required');
        }

        // Checking if user with the same username already exists
        const existingUser = users.find(u => u.username === req.body.username);
        if (existingUser) {
            throw new Error('Username already exists');
        }
        
        // Creating new user object with UUID
        const newUser = {
            id: uuidv4(), // Generating UUID
            username: req.body.username,
            password: req.body.password
        };

        // Adding new user to the array
        users.push(newUser);

        // Sending success response with status code 201 (Created)
        res.status(201).json(newUser);
    } catch (error) {
        // Handling validation errors with status code 400 (Bad Request)
        res.status(400).json({ error: error.message });
    }
}

// Controller function to get all users
function getAllUsers(req, res) {
    // Sending response with status code 200 (OK)
    res.status(200).json(users);
}

// Controller function to find user by ID
function findUserById(req, res) {
    const user = users.find(u => u.id === req.params.id);
    if (user) {
        // Sending response with status code 200 (OK) if user is found
        res.status(200).json(user);
    } else {
        // Sending response with status code 404 (Not Found) if user is not found
        res.status(404).json({ error: 'User not found' });
    }
}

module.exports = { createUser, getAllUsers, findUserById };
