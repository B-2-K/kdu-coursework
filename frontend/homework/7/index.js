// Establish WebSocket connection with server
const socket = io('http://localhost:3000');

// DOM manipulation: get input and messages elements
const input = document.getElementById('input');
const messages = document.getElementById('messages');

// Event listener for form submission
form.addEventListener('submit', (e) => {
    e.preventDefault();
    if (input.value) {
        socket.emit('chat message', input.value); // Send message to server
        input.value = '';
    }
});

// Event listener for receiving messages from server
socket.on('chat message', (data) => {
    addMessage(data); // Add message to UI
});

// Function to add a new message to the UI
function addMessage(data) {
    let messageContainer = document.createElement('div');
    messageContainer.className = 'message-format';

    let avatarImage = document.createElement('img');
    avatarImage.className = 'avatar-logo';
    avatarImage.alt = 'avatar image';
    avatarImage.src = data.id === socket.id ? 'images/you.png' : 'images/user.png';

    let textMessage = document.createElement('p');
    textMessage.className = 'text-message';
    textMessage.textContent = data.msg;

    messageContainer.appendChild(avatarImage);
    messageContainer.appendChild(textMessage);

    messages.appendChild(messageContainer);
}
