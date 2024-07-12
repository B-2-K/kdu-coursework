const express = require('express');
const cors = require('cors');
const http = require('http');
const ServerIo = require('socket.io');
const path = require('path');

const app = express();
const server = http.createServer(app);
const io = new ServerIo.Server(server, { cors: { origin: 'http://127.0.0.1:5500' } });

// Middleware
app.use(cors());
app.use(express.json());

// WebSocket handling
io.on('connection', (socket) => {
    console.log("connection established");

    socket.on('chat message', (msg) => {
        console.log('id:' + socket.id);
        console.log('message: ' + msg);
        io.emit('chat message', { id: socket.id, msg: msg });
    });

    socket.on('disconnect', () => {
        console.log("connection disconnected");
    });
});

// Routes
app.get('/', (req, res) => {
    res.send('Server is running');
});

// Server start
server.listen(3000, () => {
    console.log('App listening on port 3000!');
});
