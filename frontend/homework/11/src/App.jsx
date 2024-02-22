import logo from './logo.svg';
import './App.css';
import { useEffect, useState } from 'react';
import axios from 'axios';

function App() {
  const [data, setData] = useState([]);

  function handleClick() {
    console.log('handleClick called');
    axios.get('https://api.quotable.io/quotes/random')
      .then(res => {
        setData([...res.data, ...data]);
      })
      .catch(err => {
        console.log(err);
      });
  }

  console.log(data);

  useEffect(() => {
    axios.get('https://api.quotable.io/quotes/random?limit=3')
      .then((res) => {
        setData([...data, ...res.data]);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  return (
    <>
      <div><button onClick={handleClick}>Add Quote</button></div>
      <div>
        {data.map((item, index) => (
          <div key={index}>
            <p>{item.content}</p>
            <p>Tags : </p>
            <ul>
              {item.tags.map((tag, index) => (
                <button key={index}>{tag}</button>
              ))}
            </ul>
          </div>
        ))}
      </div>
    </>
  );
}

export default App;
