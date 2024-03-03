const express = require('express');
const cors = require('cors');
const http = require('http');
const ServerIo = require('socket.io');
const path = require('path');

const app = express();
const server = http.createServer(app);
const io = new ServerIo.Server(server, { cors: { origin: '*' } });

const corsOptions = {
    origin: '*'
};

app.use(cors(corsOptions));
app.use(express.json());

app.get('/', (req, res) => {
    res.send('Hello World!');
});

const transactions = [];
const history = [];

app.post('/history', (req, res) => {
    console.log(req.body);
    const data = {
        status: req.body.status,
        stock_name: req.body.name,
        stock_symbol: req.body.symbol,
        timestamp: req.body.date,
        transaction_price: req.body.price
    }
    history.unshift(data);
    res.send(data);
});

app.get('/history', (req, res) => {
    res.send(history);
});

app.post('/transaction', (req, res) => {
    console.log(req.body);
    transactions.unshift(req.body);
    res.send(transactions);
});

app.get('/transaction', (req, res) => {
    res.send(transactions);
});

// WebSocket handling
io.on('connection', (socket) => {
    console.log("connection established");

    // Listen for 'buy' event from the client
    socket.on('buy', (data) => {
        console.log('Buy event received:', data);
        transactions.unshift(data);
        io.emit('buy', (data));
    });

    // Listen for 'sell' event from the client
    socket.on('sell', (data) => {
        console.log('Sell event received:', data);
        transactions.unshift(data);
        io.emit('sell', (data));
    });

    const sendRandomNumber = () => {
        const randomNumber = Math.floor(Math.random() * (500 - 200 + 1) + 200); // Generate random number between 200 and 500
        io.emit('newRandomNumber', randomNumber); // Emit 'newRandomNumber' event instead of 'randomNumber'
    };

    // Send random number every 5 seconds
    const interval = setInterval(sendRandomNumber, 5000);

    socket.on('disconnect', () => {
        console.log("connection disconnected");
    });
});

// Server start
server.listen(3000, () => {
    console.log('App listening on port 3000!');
});
