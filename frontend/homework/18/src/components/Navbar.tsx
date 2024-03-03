import React from 'react';
import { useTodoContext } from './TodoContext';
import '../scss/Navbar.scss';

const Navbar: React.FC = () => {
  const { searchTerm, setSearchTerm } = useTodoContext();

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
  };

  return (
    <nav className="navbar">
      <div className="container">
        <h1>Item Lister</h1>
        <input
          type="text"
          placeholder="Search Items"
          value={searchTerm}
          onChange={handleSearchChange}
        />
      </div>
    </nav>
  );
};

export default Navbar;