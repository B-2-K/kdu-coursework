import React from 'react';
import '../styles/Filter.scss';

interface FilterProps {
  filters: string[];
  onDeleteFilter: (filter: string) => void;
}

const Filter: React.FC<FilterProps> = ({ filters, onDeleteFilter }) => {
  return (
    <div className='navigation'>
      <span>Filters</span>
      {filters.map((filter, index) => (
        <div className='filter-item__container' key={index}>
          <span className='filter-item'>{filter}</span>
          <button className='delete-button' onClick={() => onDeleteFilter(filter)}>
            X
          </button>
        </div>
      ))}
    </div>
  );
};

export default Filter;
