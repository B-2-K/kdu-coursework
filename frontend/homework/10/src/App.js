import logo from './logo.svg';
import './App.css';
import './Items.css';

import Navbar from './Navbar';
import Items from './Items';

function App() {
  return (
    <>
      <div>
        <Navbar/>
      </div>
      <div className='item-body'>
        <Items></Items>
      </div>
    </>
  );
}

export default App;
