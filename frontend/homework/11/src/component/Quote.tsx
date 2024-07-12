import React from 'react';
import { APIQuote } from '../utils/ApiQuote';
import '../styles/Quote.scss';

interface QuoteProps {
  setFilter: React.Dispatch<React.SetStateAction<string[]>>;
  quote: APIQuote;
}

const Quote: React.FC<QuoteProps> = ({ setFilter, quote }) => {
  const filterTheQuotes = (event: React.MouseEvent<HTMLSpanElement>) => {
    const selectedTag = event.currentTarget.textContent || '';
    setFilter(prevValue => {
      if (!prevValue.includes(selectedTag)) {
        return [...prevValue, selectedTag];
      }
      return prevValue;
    });
  };

  return (
    <div className='quote-container'>
      <h2>{quote.content}</h2>
      <p className='author'>~{quote.author}</p>
      <p>{quote.dateAdded}</p>
      <div className='tags-container'>
        {quote.tags.map((tag, index) => (
          <span key={index} onClick={filterTheQuotes} className='tag-item'>
            {tag}
          </span>
        ))}
      </div>
    </div>
  );
};

export default Quote;
