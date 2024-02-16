const express = require('express');
const path = require('path');
const http = require('http');
const { Server } = require('socket.io');
const cors = require('cors');

const app = express();
const server = http.createServer(app);
const io = new Server(server, { cors: { origin: '*' } });

// app.use(express.static(path.join(__dirname, 'public')));
app.use(express.json());
app.use(cors());

app.get('/', (req, res) => {
    // res.sendFile(__dirname + '/public/index.html');
    res.send("Hello World!");
});

// WebSocket handling
io.on('connection', (socket) => {
    console.log("connection established");

    // socket.on('chat message', () => {
    //     console.log('id:' + socket.id);
    //     msg = socket.id;
    //     io.emit('chat message', { id: socket.id, msg: msg });
    // });

    console.log("connect");

    socket.on("ping", (count) => {
        console.log(count);
        msg = socket.id;
        io.emit('chat message', { id: socket.id, msg: msg });
    });

    socket.on('disconnect', () => {
        console.log("connection disconnected");
    });
});

server.listen(3000, () => {
    console.log('Example app listening on port 3000!');
});