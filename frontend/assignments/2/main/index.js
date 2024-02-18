function hashtagsHandler() {
    const mainContaint = document.querySelector('.main-containt');
    const text = mainContaint.textContent;
    const hashtags = text.match(/#[^\s#]+/g);

    if (hashtags) {
        hashtags.forEach(hashtag => {
            const blueHashtag = document.createElement('span');
            blueHashtag.classList.add('blue-hashtag');
            blueHashtag.textContent = hashtag;
            mainContaint.innerHTML = mainContaint.innerHTML.replace(hashtag, blueHashtag.outerHTML);
        });
    }
}

hashtagsHandler();

function checkInput() {
    var input = document.getElementById('post-input');
    var submitBtn = document.getElementById('post-button-id');

    if (input.value.trim() === '') {
        submitBtn.classList.add('fade-out');
    } else {
        submitBtn.classList.remove('fade-out');
    }
}

document.addEventListener('DOMContentLoaded', function () {
    const profileButton = document.getElementById('profile-button');
    const profileDetails = document.getElementsByClassName('mobile-navigation')[0];
    const otherDetails = document.getElementsByClassName('mobile-post-section')[0];
    const postButton = document.getElementById('mobile-post-open-button');
    const navbar = document.getElementsByClassName('mobile-navbar')[0];

    profileButton.addEventListener('click', function () {
        if (profileDetails.style.display === 'none' || profileDetails.style.display === '') {
            profileDetails.style.display = 'block';
            otherDetails.style.display = 'none';
            postButton.style.display = 'none';
            navbar.style.display = 'none';
        } else {
            profileDetails.style.display = 'none';
            otherDetails.style.display = 'block';
            postButton.style.display = 'none';
            navbar.style.display = 'none';
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const profileButton = document.getElementById('mobile-post-open-button');
    const profileDetails = document.getElementsByClassName('mobile-tweet-section')[0];
    const otherDetails = document.getElementsByClassName('mobile-post-section')[0];
    const navbar = document.getElementsByClassName('mobile-navbar')[0];

    profileButton.addEventListener('click', function () {
        if (profileDetails.style.display === 'none' || profileDetails.style.display === '') {
            profileDetails.style.display = 'block';
            otherDetails.style.display = 'none';
            navbar.style.display = 'none';
        } else {
            profileDetails.style.display = 'none';
            otherDetails.style.display = 'block';
            navbar.style.display = 'flex';
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const profileButton = document.getElementById('back-button-id');
    const profileDetails = document.getElementsByClassName('mobile-tweet-section')[0];
    const otherDetails = document.getElementsByClassName('mobile-post-section')[0];
    const navbar = document.getElementsByClassName('mobile-navbar')[0];

    profileButton.addEventListener('click', function () {
        profileDetails.style.display = 'none';
        otherDetails.style.display = 'block';
        navbar.style.display = 'flex';
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const profileButton = document.getElementById('mobile-post-buttton-id');
    const profileDetails = document.getElementsByClassName('mobile-tweet-section')[0];
    const otherDetails = document.getElementsByClassName('mobile-post-section')[0];
    const navbar = document.getElementsByClassName('mobile-navbar')[0];

    profileButton.addEventListener('click', function () {
        profileDetails.style.display = 'none';
        otherDetails.style.display = 'block';
        navbar.style.display = 'flex';
    });
});

isLiked = false;
function toggleButtons(button) {
    const svg = button.querySelector('svg');

    if (isLiked === false) {
        isLiked = true;
        button.innerHTML = `
        <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi" style="fill: rgb(241, 58, 128);">
                                            <g>
                                              <path d="M20.884 13.19c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path>
                                            </g>
                                          </svg>
    `;
    }
    else {
        isLiked = false;
        button.innerHTML = `
        <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi" style="fill: gray;">
            <g>
                <path d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path>
            </g>
        </svg>
    `;
    }
}

var currentId = 0;
document.addEventListener("DOMContentLoaded", function () {
    var postButton = document.getElementById('post-button-id')
    postButton.addEventListener("click", function () {
        var postInput = document.getElementById("post-input").value;
        var postData = postInput;
        fetch('/api/posts', {
            method: 'POST',
            body: postData
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to create post');
                }
                return response.json();
            })
            .then(data => {
                // Add newly created post to the top of the list
                // prependNewPost(data);
                // document.getElementById("post-input").value = "";
            })
            .catch(error => {
                console.error('Error:', error);
                alert("Failed to create post. Please try again.");
            });

        emitSocketEvent(postInput);
        console.log(postInput);
    });
});

document.addEventListener("DOMContentLoaded", function () {
    var postButton = document.getElementById('mobile-post-buttton-id')
    postButton.addEventListener("click", function () {
        var postInput = document.getElementById("mobile-post-input").value;
        console.log(postInput);
        if (postInput.trim() !== "") {
            var newPost = document.createElement("div");
            newPost.classList.add("posts");
            newPost.innerHTML = `
                <div class="left">
                    <div class="nav-profile-image">
                        <img src="./images/Profile/profile pic.png" alt="" srcset="">
                    </div>
                    <div class="main-post">
                        <div class="user-details">
                            <b>Nitesh Gupta</b> <span style="color: gray;"> @nit_hck - 1s</span>
                        </div>
                        <div class="main-containt">
                            ${postInput}
                        </div>
                        <br>
                        <div class="main-post-icons">
                            <div>
                                <svg viewBox="0 0 24 24" aria-hidden="true"
                                    class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                    <g>
                                        <path fill="#808080"
                                            d="M1.751 10c0-4.42 3.584-8 8.005-8h4.366c4.49 0 8.129 3.64 8.129 8.13 0 2.96-1.607 5.68-4.196 7.11l-8.054 4.46v-3.69h-.067c-4.49.1-8.183-3.51-8.183-8.01zm8.005-6c-3.317 0-6.005 2.69-6.005 6 0 3.37 2.77 6.08 6.138 6.01l.351-.01h1.761v2.3l5.087-2.81c1.951-1.08 3.163-3.13 3.163-5.36 0-3.39-2.744-6.13-6.129-6.13H9.756z">
                                        </path>
                                    </g>
                                </svg>

                            </div>
                            <div>
                                <svg viewBox="0 0 24 24" aria-hidden="true"
                                    class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                    <g>
                                        <path fill="#808080"
                                            d="M4.5 3.88l4.432 4.14-1.364 1.46L5.5 7.55V16c0 1.1.896 2 2 2H13v2H7.5c-2.209 0-4-1.79-4-4V7.55L1.432 9.48.068 8.02 4.5 3.88zM16.5 6H11V4h5.5c2.209 0 4 1.79 4 4v8.45l2.068-1.93 1.364 1.46-4.432 4.14-4.432-4.14 1.364-1.46 2.068 1.93V8c0-1.1-.896-2-2-2z">
                                        </path>
                                    </g>
                                </svg>

                            </div>

                            <div>
                                <button id="like-button-desktop" type="button" onclick="toggleButtons(this)">
                                    <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi" style="fill: gray;">
                                        <g>
                                            <path d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path>
                                        </g>
                                    </svg>
                                </button>
                            </div>

                            <div>
                                <svg viewBox="0 0 24 24" aria-hidden="true"
                                    class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                    <g>
                                        <path fill="#808080"
                                            d="M8.75 21V3h2v18h-2zM18 21V8.5h2V21h-2zM4 21l.004-10h2L6 21H4zm9.248 0v-7h2v7h-2z">
                                        </path>
                                    </g>
                                </svg>

                            </div>
                            <div>
                                <svg viewBox="0 0 24 24" aria-hidden="true"
                                    class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                    <g>
                                        <path fill="#808080"
                                            d="M4 4.5C4 3.12 5.119 2 6.5 2h11C18.881 2 20 3.12 20 4.5v18.44l-8-5.71-8 5.71V4.5zM6.5 4c-.276 0-.5.22-.5.5v14.56l6-4.29 6 4.29V4.5c0-.28-.224-.5-.5-.5h-11z">
                                        </path>
                                    </g>
                                </svg>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="more-repost-section">
                    <div>
                        <svg viewBox="0 0 24 24" aria-hidden="true"
                            class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                            <g>
                                <path fill="#808080"
                                    d="M3 12c0-1.1.9-2 2-2s2 .9 2 2-.9 2-2 2-2-.9-2-2zm9 2c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zm7 0c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2z">
                                </path>
                            </g>
                        </svg>

                    </div>
                    <div>
                        <svg viewBox="0 0 24 24" aria-hidden="true"
                            class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                            <g>
                                <path fill="#808080"
                                    d="M12 2.59l5.7 5.7-1.41 1.42L13 6.41V16h-2V6.41l-3.3 3.3-1.41-1.42L12 2.59zM21 15l-.02 3.51c0 1.38-1.12 2.49-2.5 2.49H5.5C4.11 21 3 19.88 3 18.5V15h2v3.5c0 .28.22.5.5.5h12.98c.28 0 .5-.22.5-.5L19 15h2z">
                                </path>
                            </g>
                        </svg>

                    </div>
                </div>
            `;

            var postContainer = document.querySelector(".mobile-post-section");
            postContainer.prepend(newPost);
            document.getElementById("post-input").value = "";
            hashtagsHandler();
        } else {
            alert("Please enter a post message.");
        }
    });
});

document.addEventListener("DOMContentLoaded", function() {
    const messageBtn = document.getElementById("message-btn");

    const postSection = document.getElementsByClassName("post-section")[0];

    const chatSection = document.getElementsByClassName("chat-section")[0];

    const chats = document.getElementsByClassName("chats")[0];
    
    messageBtn.addEventListener("click", function(e) {
        postSection.style.display = 'none';
        chatSection.style.display = 'flex';
        chats.style.display = 'flex';
    });
});


// Establish WebSocket connection with server
const socket = io('http://localhost:3000');

function emitSocketEvent(data) {
    console.log('emitSocketEvent complete');
    socket.emit('post message', data);
}

// Event listener for receiving messages from server
socket.on('post message', (data) => {
    console.log("received message:");
    console.log(`Received message from server: ${data.msg}`);

    var postInput = document.getElementById("post-input").value;
    postInput = data.msg;
    console.log(postInput);
    if (postInput.trim() !== "") {
        var newPost = document.createElement("div");
        newPost.classList.add("posts");

        var postId = "post-" + currentId;
        currentId++;

        newPost.setAttribute("id", postId);

        newPost.innerHTML = `
                <div class="left">
                    <div class="nav-profile-image">
                        <img src="./images/Profile/profile pic.png" alt="" srcset="">
                    </div>
                    <div class="main-post">
                        <div class="user-details">
                            <b>Nitesh Gupta</b> <span style="color: gray;"> @nit_hck - 1s</span>
                        </div>
                        <div class="main-containt">
                            ${postInput}
                        </div>
                        <br>
                        <div class="main-post-icons">
                        <div>
                            <svg viewBox="0 0 24 24" aria-hidden="true"
                                class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                <g>
                                    <path fill="#808080"
                                        d="M1.751 10c0-4.42 3.584-8 8.005-8h4.366c4.49 0 8.129 3.64 8.129 8.13 0 2.96-1.607 5.68-4.196 7.11l-8.054 4.46v-3.69h-.067c-4.49.1-8.183-3.51-8.183-8.01zm8.005-6c-3.317 0-6.005 2.69-6.005 6 0 3.37 2.77 6.08 6.138 6.01l.351-.01h1.761v2.3l5.087-2.81c1.951-1.08 3.163-3.13 3.163-5.36 0-3.39-2.744-6.13-6.129-6.13H9.756z">
                                    </path>
                                </g>
                            </svg>

                        </div>
                        <div>
                            <svg viewBox="0 0 24 24" aria-hidden="true"
                                class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                <g>
                                    <path fill="#808080"
                                        d="M4.5 3.88l4.432 4.14-1.364 1.46L5.5 7.55V16c0 1.1.896 2 2 2H13v2H7.5c-2.209 0-4-1.79-4-4V7.55L1.432 9.48.068 8.02 4.5 3.88zM16.5 6H11V4h5.5c2.209 0 4 1.79 4 4v8.45l2.068-1.93 1.364 1.46-4.432 4.14-4.432-4.14 1.364-1.46 2.068 1.93V8c0-1.1-.896-2-2-2z">
                                    </path>
                                </g>
                            </svg>

                        </div>

                        <div>
                            <button id="like-button-desktop" type="button" onclick="toggleButtons(this)">
                                <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi" style="fill: gray;">
                                    <g>
                                        <path d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path>
                                    </g>
                                </svg>
                            </button>
                        </div>
                        
                        <div>
                            <svg viewBox="0 0 24 24" aria-hidden="true"
                                class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                <g>
                                    <path fill="#808080"
                                        d="M8.75 21V3h2v18h-2zM18 21V8.5h2V21h-2zM4 21l.004-10h2L6 21H4zm9.248 0v-7h2v7h-2z">
                                    </path>
                                </g>
                            </svg>

                        </div>
                        <div>
                            <svg viewBox="0 0 24 24" aria-hidden="true"
                                class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                <g>
                                    <path fill="#808080"
                                        d="M4 4.5C4 3.12 5.119 2 6.5 2h11C18.881 2 20 3.12 20 4.5v18.44l-8-5.71-8 5.71V4.5zM6.5 4c-.276 0-.5.22-.5.5v14.56l6-4.29 6 4.29V4.5c0-.28-.224-.5-.5-.5h-11z">
                                    </path>
                                </g>
                            </svg>

                        </div>
                    </div>
                    </div>
                </div>
                <div class="more-repost-section">
                        <div>
                            <svg viewBox="0 0 24 24" aria-hidden="true"
                                class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                <g>
                                    <path fill="#808080"
                                        d="M3 12c0-1.1.9-2 2-2s2 .9 2 2-.9 2-2 2-2-.9-2-2zm9 2c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zm7 0c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2z">
                                    </path>
                                </g>
                            </svg>

                        </div>
                        <div>
                            <svg viewBox="0 0 24 24" aria-hidden="true"
                                class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                <g>
                                    <path fill="#808080"
                                        d="M12 2.59l5.7 5.7-1.41 1.42L13 6.41V16h-2V6.41l-3.3 3.3-1.41-1.42L12 2.59zM21 15l-.02 3.51c0 1.38-1.12 2.49-2.5 2.49H5.5C4.11 21 3 19.88 3 18.5V15h2v3.5c0 .28.22.5.5.5h12.98c.28 0 .5-.22.5-.5L19 15h2z">
                                    </path>
                                </g>
                            </svg>

                        </div>
                    </div>
            `;

        var postContainer = document.querySelector(".new-posts");
        postContainer.prepend(newPost);
        console.log(postId);
        document.getElementById("post-input").value = "";
        hashtagsHandler();
    } else {
        alert("Please enter a post message.");
    }
    document.getElementById("post-input").value = "";
});


// DOM manipulation: get input and messages elements
const input = document.getElementById('chat-input');
const messages = document.getElementById('chat-messages');

// Event listener for form submission
form.addEventListener('submit', (e) => {
    e.preventDefault();
    if (input.value) {
        socket.emit('chat message', input.value); // Send message to server
        let messageContainer = document.createElement('div');
        messageContainer.className = 'right'; // Add right class
        let textMessage = document.createElement('p');
        textMessage.className = 'text-message';
        textMessage.textContent = input.value;

        messageContainer.appendChild(textMessage);
        messages.appendChild(messageContainer);

        var chatSections = document.getElementsByClassName('chat-section');
        if (chatSections.length > 0) {
            chatSections[0].style.display = 'flex';
        }
    }
});


socket.on('new user', (data) => {
    addButton(data);
});


// Event listener for receiving messages from server
socket.on('chat message', (data) => {
    addMessage(data); // Add message to UI
});

socket.on('user disconnected', (data) => {
    removeButton(data);
});

socket.on('add new users', (data) => {
    onlineUsersList = data.onlineUsersList;
    users = data.users;
    console.log(onlineUsersList);
    console.log(users);
    for (var i = 0; i < onlineUsersList.length; i++) {
        const user = users.find(user => user.id === onlineUsersList[i]);
        console.log("add button for user" , user.userName);
        addButton({id: onlineUsersList[i], name: user.userName});
    }
});

socket.on('load msg', (data) => {
    // console.log("data loaded");
    // var allMessagesList = data.allMessagesList;
    console.log("all messages list: " + data.allMessagesList[0].sender);
    const allMessagesList = data.allMessagesList;

    // for(var i = 0; i < allMessagesList.length;i++){
    //     console.log(allMessagesList[i]);
    //     var msg = allMessagesList[i][2];
    //     console.log(msg);
    //     addMessage({ id: socket.id, msg: msg});
    // }
    // console.log(allMessagesList);
})

function removeButton(socketId) {
    var allButtons = document.getElementsByClassName('chat-btn');
    for (var i = 0; i < allButtons.length; i++) {
        if (allButtons[i].id === socketId) {
            allButtons[i].remove();
        }
    }
}

// Function to add a new message to the UI
function addMessage(data) {
    console.log('socket.id: ' + socket.id + ' message: ' + JSON.stringify(data) + 'data.id: ' + data.id);
    let messageContainer = document.createElement('div');
    messageContainer.className = 'message-format';

    var id = 'message-' + data.id;
    messageContainer.id = id;
    let textMessage = document.createElement('p');
    textMessage.className = 'text-message';
    textMessage.textContent = data.msg;

    // messageContainer.appendChild(avatarImage);
    messageContainer.appendChild(textMessage);

    messages.appendChild(messageContainer);

    var id = 'message-' + data.id;

    var chatSections = document.getElementsByClassName('chat-section');
    if (chatSections.length > 0) {
        chatSections[0].style.display = 'flex';
    }

    // addButton(data.id);

    messages.scrollTo(0, document.body.scrollHeight);
}

function addButton(data) {
    var socketId = data.id;
    var allButtons = document.getElementsByClassName('chat-btn');
    var isButton = false;
    console.log("Try to create chat button");
    for (var i = 0; i < allButtons.length; i++) {
        if (allButtons[i].id == socketId) {
            console.log("button is already present");
            isButton = true;
        }
    }
    console.log(isButton);
    if (!isButton) {
        console.log(socketId);
        var chatsList = document.getElementsByClassName('chats')[0];
        var chatSection = document.createElement('button');
        chatSection.className = 'chat-btn';
        chatSection.id = socketId;
        console.log(data.name);
        chatSection.textContent = data.name;
        // chatSection.style.height = '50px';

        // Assigning an anonymous function to handle the click event
        // chatSection.onclick = function () {
        //     handleChatMessageClick(this); // Passes the button element as an argument
        // };

        chatsList.appendChild(chatSection);
    }
    else {
        console.log("button is already present");
    }
}

function handleChatMessageClick(button) {
    console.log('handleChatMessageClick');
    console.log(button.id);
    socket.emit('id', button.id);
    var chatSections = document.getElementsByClassName('chat-section');
    // if (chatSections.length > 0) {
    chatSections[0].style.display = 'flex';
    // }

    var allMessages = document.getElementsByClassName('message-format');
    for (var i = 0; i < allMessages.length; i++) {
        if (allMessages[i].id === button.id) {
            allMessages[i].style.display = 'block';
        }
        else {
            allMessages[i].style.display = 'none';
        }
    }
}

// function displayMessages(){

// }



let page = 1;
const pageSize = 5;
let isLoading = false;

document.addEventListener('DOMContentLoaded', () => {
    loadPosts();
    window.addEventListener('scroll', handleScroll);
});

function loadPosts() {
    if (isLoading) return;
    isLoading = true;

    fetch(`/api/posts?page=${page}&pageSize=${pageSize}`)
        .then(response => response.json())
        .then(data => {
            appendPosts(data.data);
            page++;
            isLoading = false;
        })
        .catch(error => {
            console.error('Error loading posts:', error);
            isLoading = false;
        });
}

function appendPosts(posts) {

    posts.forEach(post => {
        postInput = post;
        console.log(postInput);
        // if (postInput.trim() !== "") {
        var newPost = document.createElement("div");
        newPost.classList.add("posts");

        var postId = "post-" + currentId;
        currentId++;

        newPost.setAttribute("id", postId);

        newPost.innerHTML = `
                <div class="left">
                    <div class="nav-profile-image">
                        <img src="./images/Profile/profile pic.png" alt="" srcset="">
                    </div>
                    <div class="main-post">
                        <div class="user-details">
                            <b>Nitesh Gupta</b> <span style="color: gray;"> @nit_hck - 1s</span>
                        </div>
                        <div class="main-containt">
                            ${postInput}
                        </div>
                        <br>
                        <div class="main-post-icons">
                        <div>
                            <svg viewBox="0 0 24 24" aria-hidden="true"
                                class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                <g>
                                    <path fill="#808080"
                                        d="M1.751 10c0-4.42 3.584-8 8.005-8h4.366c4.49 0 8.129 3.64 8.129 8.13 0 2.96-1.607 5.68-4.196 7.11l-8.054 4.46v-3.69h-.067c-4.49.1-8.183-3.51-8.183-8.01zm8.005-6c-3.317 0-6.005 2.69-6.005 6 0 3.37 2.77 6.08 6.138 6.01l.351-.01h1.761v2.3l5.087-2.81c1.951-1.08 3.163-3.13 3.163-5.36 0-3.39-2.744-6.13-6.129-6.13H9.756z">
                                    </path>
                                </g>
                            </svg>

                        </div>
                        <div>
                            <svg viewBox="0 0 24 24" aria-hidden="true"
                                class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                <g>
                                    <path fill="#808080"
                                        d="M4.5 3.88l4.432 4.14-1.364 1.46L5.5 7.55V16c0 1.1.896 2 2 2H13v2H7.5c-2.209 0-4-1.79-4-4V7.55L1.432 9.48.068 8.02 4.5 3.88zM16.5 6H11V4h5.5c2.209 0 4 1.79 4 4v8.45l2.068-1.93 1.364 1.46-4.432 4.14-4.432-4.14 1.364-1.46 2.068 1.93V8c0-1.1-.896-2-2-2z">
                                    </path>
                                </g>
                            </svg>

                        </div>

                        <div>
                            <button id="like-button-desktop" type="button" onclick="toggleButtons(this)">
                                <svg viewBox="0 0 24 24" aria-hidden="true" class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi" style="fill: gray;">
                                    <g>
                                        <path d="M16.697 5.5c-1.222-.06-2.679.51-3.89 2.16l-.805 1.09-.806-1.09C9.984 6.01 8.526 5.44 7.304 5.5c-1.243.07-2.349.78-2.91 1.91-.552 1.12-.633 2.78.479 4.82 1.074 1.97 3.257 4.27 7.129 6.61 3.87-2.34 6.052-4.64 7.126-6.61 1.111-2.04 1.03-3.7.477-4.82-.561-1.13-1.666-1.84-2.908-1.91zm4.187 7.69c-1.351 2.48-4.001 5.12-8.379 7.67l-.503.3-.504-.3c-4.379-2.55-7.029-5.19-8.382-7.67-1.36-2.5-1.41-4.86-.514-6.67.887-1.79 2.647-2.91 4.601-3.01 1.651-.09 3.368.56 4.798 2.01 1.429-1.45 3.146-2.1 4.796-2.01 1.954.1 3.714 1.22 4.601 3.01.896 1.81.846 4.17-.514 6.67z"></path>
                                    </g>
                                </svg>
                            </button>
                        </div>
                        
                        <div>
                            <svg viewBox="0 0 24 24" aria-hidden="true"
                                class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                <g>
                                    <path fill="#808080"
                                        d="M8.75 21V3h2v18h-2zM18 21V8.5h2V21h-2zM4 21l.004-10h2L6 21H4zm9.248 0v-7h2v7h-2z">
                                    </path>
                                </g>
                            </svg>

                        </div>
                        <div>
                            <svg viewBox="0 0 24 24" aria-hidden="true"
                                class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                <g>
                                    <path fill="#808080"
                                        d="M4 4.5C4 3.12 5.119 2 6.5 2h11C18.881 2 20 3.12 20 4.5v18.44l-8-5.71-8 5.71V4.5zM6.5 4c-.276 0-.5.22-.5.5v14.56l6-4.29 6 4.29V4.5c0-.28-.224-.5-.5-.5h-11z">
                                    </path>
                                </g>
                            </svg>

                        </div>
                    </div>
                    </div>
                </div>
                <div class="more-repost-section">
                        <div>
                            <svg viewBox="0 0 24 24" aria-hidden="true"
                                class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                <g>
                                    <path fill="#808080"
                                        d="M3 12c0-1.1.9-2 2-2s2 .9 2 2-.9 2-2 2-2-.9-2-2zm9 2c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zm7 0c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2z">
                                    </path>
                                </g>
                            </svg>

                        </div>
                        <div>
                            <svg viewBox="0 0 24 24" aria-hidden="true"
                                class="r-4qtqp9 r-yyyyoo r-dnmrzs r-bnwqim r-1plcrui r-lrvibr r-1xvli5t r-1hdv0qi">
                                <g>
                                    <path fill="#808080"
                                        d="M12 2.59l5.7 5.7-1.41 1.42L13 6.41V16h-2V6.41l-3.3 3.3-1.41-1.42L12 2.59zM21 15l-.02 3.51c0 1.38-1.12 2.49-2.5 2.49H5.5C4.11 21 3 19.88 3 18.5V15h2v3.5c0 .28.22.5.5.5h12.98c.28 0 .5-.22.5-.5L19 15h2z">
                                    </path>
                                </g>
                            </svg>

                        </div>
                    </div>
            `;

        var postContainer = document.querySelector(".new-posts");
        postContainer.append(newPost);
        console.log(postId);
        document.getElementById("post-input").value = "";
        hashtagsHandler();
        // } else {
        //     alert("Please enter a post message.");
        // }
    });
}

function handleScroll() {
    const { scrollTop, scrollHeight, clientHeight } = document.documentElement;
    if (scrollTop + clientHeight >= scrollHeight - 5) {
        loadPosts();
    }
}

