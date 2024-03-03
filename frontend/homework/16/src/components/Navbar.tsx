// Navbar.tsx
import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setSearchTerm } from '../redux/todoSlice';
import { RootState } from '../redux/store';
import '../scss/Navbar.scss';

const Navbar: React.FC = () => {
  const searchTerm = useSelector((state: RootState) => state.todo.searchTerm);
  const dispatch = useDispatch();

  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    dispatch(setSearchTerm(e.target.value));
  };

  return (
    <nav className="navbar">
      <div className="container">
        <h1>Item Lister</h1>
        <input
          type="text"
          placeholder="Search Items.."
          value={searchTerm}
          onChange={handleSearchChange}
        />
      </div>
    </nav>
  );
};

export default Navbar;
