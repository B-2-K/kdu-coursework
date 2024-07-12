const express = require('express');
const userRoutes = require('./userRoutes');

const app = express();

// Middleware to parse JSON bodies
app.use(express.json());

// Mounting user routes
app.use('/users', userRoutes);

// Catch-all route for undefined routes
app.use((req, res) => {
    res.status(404).json({ error: 'Enter the valid URL. Endpoint not found' });
});

module.exports = app;