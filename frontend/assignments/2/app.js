const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const http = require('http');
const ServerIo = require('socket.io');
const path = require('path');

const app = express();
const server = http.createServer(app);
const io = new ServerIo.Server(server, { cors: { origin: '*' } });

const port = 3000;

app.use(cors());
app.use(express.json());
app.use(bodyParser.json());

// Dummy users data
const users = [
    { id: 1, userName: 'user', password: '1234', name: 'User One' },
    { id: 2, userName: 'user1', password: '1234', name: 'User Two' },
    { id: 3, userName: 'user2', password: '1234', name: 'User Three' }
];

// All messages list 
const allMessagesList = [
    {
        sender: 'user',
        receiver: 'user1',
        message: 'Hello World!'
    }
]

var noOfPosts = 0;
// const postMessage = [];

// Online Users List
const onlineUsersList = [];
var user_name;

// WebSocket handling
io.on('connection', (socket) => {
    var id = "";
    console.log("connection established");
    console.log(socket.id);

    // find users with username user_name
    var user = users.find(user => user.userName === user_name);
    console.log(user_name);
    if (user) {
        user.id = socket.id;
    } else {
        console.log("User not found for username:", user_name);
        // Handle this case according to your application's logic
    }


    onlineUsersList.push(socket.id);
    console.log("list on online users : " + onlineUsersList);
    socket.emit('add new users', ({ onlineUsersList: onlineUsersList, users: users }));


    io.except(socket.id).emit('new user', { id: socket.id, name: user_name });

    socket.on('post message', (msg) => {
        console.log('id:' + socket.id);
        console.log('message: ' + msg);
        io.emit('post message', { id: socket.id, msg: msg });
    });

    socket.on('id', (socketId) => {
        console.log(socketId);
        id = socketId;
        io.to(socket.id).emit('load msg', { receiver: id, sender: socket.id, allMessagesList: allMessagesList });
    });

    socket.on('chat message', (msg) => {
        console.log('id:' + socket.id);
        console.log('message: ' + msg);
        console.log('receiver : ', id);

        allMessagesList.push({ sender: socket.id, receiver: id, message: msg });

        io.to(id).emit('chat message', { id: socket.id, msg: msg, allMessagesList: allMessagesList });
        console.log('all messages list', allMessagesList);
    });

    socket.on('disconnect', () => {
        console.log("connection disconnected");
        io.emit('user disconnected', socket.id);
        onlineUsersList.splice(onlineUsersList.indexOf(socket.id), 1);
        console.log("list on online users : " + onlineUsersList);
    });
});

// Sample posts data
const postMessage = [
    'post1',
    'post2',
    'post3',
    'post4',
    'post5',
    'post6',
    'post7',
    'post8',
    'post9',
    'post10',
    'post11',
    'post12',
    'post13',
    'post14',
    'post15',
    'post16'
];

// Login route handler
app.get('/api/user/login', (req, res) => {
    // Here you can send a redirect response to the login page
    res.redirect('/login.html');
});

// Login route handler
app.post('/api/user/login', (req, res) => {
    const { username, password } = req.body;

    // Find the user with the provided username
    const user = users.find(user => user.userName === username);

    // Check if the user exists and if the password matches
    if (user && user.password === password) {
        console.log('authentication successful');
        // Authentication successful
        user_name = user.userName;
        console.log(user_name);
        res.status(200).json({ success: true, message: "Login successful" });
    } else {
        // Authentication failed
        res.status(401).json({ success: false, message: "Invalid username or password" });
    }
});

app.post('/api/posts', (req, res) => {
    try {
        const text = req.body.text;
        console.log(text);
        noOfPosts++;
        postMessage.push({ id: noOfPosts, text: text });
        res.status(200).json({ success: true, message: "post successfully" });
    }
    catch (error) {
        console.log(error);
    }
});

// Endpoint to fetch paginated posts
app.get('/api/posts', (req, res) => {
    try {
        const page = parseInt(req.query.page) || 1;
        const pageSize = parseInt(req.query.pageSize) || 5;
        const startIndex = (page - 1) * pageSize;
        const endIndex = startIndex + pageSize;

        const paginatedPosts = postMessage.slice(startIndex, endIndex);

        if (paginatedPosts.length === 0) {
            return res.status(404).json({ error: 'No posts found' });
        }

        res.json({
            page: page,
            pageSize: pageSize,
            totalPosts: postMessage.length,
            data: paginatedPosts
        });
    } catch (error) {
        console.error('Error fetching posts:', error);
        res.status(500).json({ error: 'Internal server error' });
    }
});
app.get('/', (req, res) => {
    res.send("chal rh hai");
})

// Serve static files from the 'main' folder
app.use('/main', express.static('main'));

// Start server
server.listen(port, () => {
    console.log(`Server is listening at http://localhost:${port}`);
});
