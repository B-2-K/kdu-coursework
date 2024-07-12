import React from 'react';
import '../scss/Navbar.scss';

interface NavbarProps {
  searchTerm: string;
  onSearchChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const Navbar: React.FC<NavbarProps> = ({ searchTerm, onSearchChange }) => {
  return (
    <nav className="navbar">
      <div className="container">
        <div>Item Lister</div>
        <div>
          <input
            type="text"
            placeholder="Search Items"
            value={searchTerm}
            onChange={onSearchChange}
          />
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
