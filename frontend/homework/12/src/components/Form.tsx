import React, { useEffect, useRef, useState } from "react";
import { form, btn } from "../styles/Form.styles";

function Form() {
  const searchInputRef = useRef<HTMLInputElement>(null);
  const nameInputRef = useRef<HTMLInputElement>(null);
  const [nameInput, setNameInput] = useState<string>("");

  useEffect(() => {
    if (searchInputRef.current) {
      searchInputRef.current.focus();
    }
  }, []); // Run only once after initial render

  const handleNameInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setNameInput(value);
    if (nameInputRef.current) {
      nameInputRef.current.innerText = value;
    }
  };

  const handleSubmit = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault(); // Prevent default form submission behavior
    // Your submit logic here, if any
    // Set focus on the first input
    if (searchInputRef.current) {
      searchInputRef.current.focus();
    }
  };

  return (
    <form style={form} onSubmit={handleSubmit}>
      <div>
        <label htmlFor="search">Search:</label>
        <input type="text" id="search" ref={searchInputRef} />
      </div>
      <div>
        <label htmlFor="name">Name:</label>
        <input
          type="text"
          id="name"
          onChange={handleNameInputChange}
          value={nameInput}
          ref={nameInputRef}
        />
      </div>
      <button style={btn} type="submit">Submit</button>
      <p>
        Welcome <span ref={nameInputRef}>User</span>
      </p>
    </form>
  );
}

export default Form;
