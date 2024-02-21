import React, { useState } from 'react';
import './Navbar.css';

function Navbar() {
  const [input, setInput] = useState();
  function handleChange(){
    setInput(document.getElementById('input').value);
  }
  return (
    <div className='navbar'>
      <div>Item Lister</div>
      <div><input id='input' type="text" value="" placeholder='Search Items'onChange={handleChange}/></div>
    </div>
  )
}

export default Navbar;
