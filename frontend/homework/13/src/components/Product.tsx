import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/product.scss';

interface Product {
  name: string;
  model: string;
  madeBy: string;
  price: number;
  image: string;
  description: string;
}

const Item: React.FC<{ product: Product }> = ({ product }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/product/${product.name}`, { state: { product : product} });
  };

  return (
    <div className="product" onClick={handleClick}>
      <div className="product-img">
        <img src={product.image} alt="product-image" />
      </div>
      <div className="product-info">
        <p className="product-name">{product.name}</p>
        <p className="product-price">${product.price}</p>
      </div>
    </div>
  );
};

export default Item;
