import React from 'react'
import Item from './Item'
import './Items.css'

function Items() {
  return (
    <>
      <div className="items-container">
        <div className='item-heading'>Add Items</div>
        <div className='items'>
          <Item></Item>
        </div>
      </div>
    </>
  )
}

export default Items