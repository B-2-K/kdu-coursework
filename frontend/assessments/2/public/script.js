function handleClick() {
    const input = document.getElementById("input");
    const button = document.getElementById("submit");
    const messages = document.querySelector(".messages");
    
    // Get current date and time
    const currentDate = new Date();
    const dateTimeString = currentDate.toLocaleString();

    // Create a new div element
    const element = document.createElement("div");
    element.className = "transactions";

    // Add content to the div
    element.innerHTML = `<span style="color: green;">SELL</span>${input.value} Stocks<br>${dateTimeString}`;

    element.style.border = "1px solid";
    element.style.padding = "5px";
    element.style.width = "95%";
    element.style.height = "100%";

    // Append the new element to the messages container
    messages.appendChild(element);

    // Clear input field
    input.value = "";
}


function handleClickRed() {
    const input = document.getElementById("input");
    const button = document.getElementById("submit");
    const messages = document.querySelector(".messages");
    
    // Get current date and time
    const currentDate = new Date();
    const dateTimeString = currentDate.toLocaleString();

    // Create a new div element
    const element = document.createElement("div");
    element.className = "transactions";

    // Add content to the div
    element.innerHTML = `<span style="color: red;">BUY</span>${input.value} Stocks<br>${dateTimeString}`;

    element.style.border = "1px solid";
    element.style.padding = "5px";
    element.style.width = "95%";
    element.style.height = "100%";

    // Append the new element to the messages container
    messages.appendChild(element);

    // Clear input field
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


// Function to add a new message to the UI
function addMessage(data) {
    let messageContainer = document.createElement('div');
    messageContainer.classList.add('bar');
    arr = [];
    for(i = 0; i < 60;i++){
        const val = randomInteger();
        let bar = document.createElement('div');
        bar.style.height = val + 'px';
        bar.style.width = '50px';
        bar.style.backgroundColor = getRandomColor();
        arr.push(val);
        bar.style.alignSelf = 'flex-end'; 
        bar.style.margin = '1px';
        messageContainer.appendChild(bar);
    }
    messages.innerHTML = messageContainer.innerHTML;

    pricetag.innerHTML = `${randomInteger()}`;
}


function getRandomColor() {
    const randomNumber = Math.random();
    if (randomNumber < 0.5) {
        return 'red';
    } else {
        return 'green';
    }
}