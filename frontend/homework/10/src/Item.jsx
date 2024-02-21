import React, { useState } from 'react';
import './Item.css';

const Item = () => {
  // State to hold the value of the input field
  const [inputValue, setInputValue] = useState('');

  // Function to handle changes in the input field
  const handleInputChange = (e) => {
    setInputValue(e.target.value);
  };

  const handleClick = () => {
    if (inputValue.trim() === '') {
      return; // Prevent adding empty messages
    }

    const messages = document.getElementById('messages');
    const msgContainer = document.createElement('div');
    msgContainer.className = 'messageList';

    // Message content
    const div = document.createElement('div');
    div.innerText = inputValue;

    // Delete button
    const deleteButton = document.createElement('button');
    deleteButton.textContent = 'Delete';
    deleteButton.onclick = () => {
      // Remove the message container when delete button is clicked
      messages.removeChild(msgContainer);
    };

    // Appending elements
    msgContainer.appendChild(div);
    msgContainer.appendChild(deleteButton);
    messages.prepend(msgContainer);
    setInputValue('');
  };

  return (
    <div className="item">
      <div className="input-and-button">
        <input
          id="todos"
          type="text"
          value={inputValue}
          onChange={handleInputChange}
        />
        <button type="button" onClick={handleClick}>
          Add
        </button>
      </div>
      <div className='item-list-heading'>Items</div>
      <div id="messages"></div>
    </div>
  );
};

export default Item;
