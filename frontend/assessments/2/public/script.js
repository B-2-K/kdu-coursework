function handleClick() {
    const input = document.getElementById("input");
    const button = document.getElementById("submit");
    const messages = document.querySelector(".messages");
    console.log("clicked");
    const element = document.createElement("div");

    element.className = "transactions";
    element.style.backgroundColor = 'green';
    element.textContent = `SELL : quantity ${input.value}`;
    messages.appendChild(element);
    input.value = "";
}

function handleClickRed() {
    const input = document.getElementById("input");
    const button = document.getElementById("submit");
    const messages = document.querySelector(".messages");
    console.log("clicked");
    const element = document.createElement("div");

    element.className = "transactions";
    element.style.backgroundColor = 'red';
    element.textContent = `BUY : quantity ${input.value}`;
    messages.appendChild(element);
    input.value = "";
}

// Establish WebSocket connection with server
const socket = io('http://localhost:3000');

let count = 0;
setInterval(() => {
    console.log(count);
    socket.emit("ping", ++count);
}, 5000);

// DOM manipulation: get input and messages elements
const input = document.getElementById('graph-input');
const messages = document.getElementById('messages');

// Event listener for receiving messages from server
socket.on('chat message', (data) => {
    addMessage(data); // Add message to UI
});

const min = 0;
const max = 500;
function randomInteger() {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

const pricetag = document.getElementById('price');
pricetag.innerHTML = `${randomInteger()}`;

// Function to add a new message to the UI
function addMessage(data) {
    let messageContainer = document.createElement('div');
    arr = [];
    for(i = 0; i < 60;i++){
        const val = randomInteger();
        let bar = document.createElement('div');
        bar.style.height = val + 'px';
        bar.style.width = '10px';
        bar.style.backgroundColor = 'blue';
        arr.push(val);
        messages.appendChild(bar);
    }
    messages.scrollTo(0, document.body.scrollHeight);
}
