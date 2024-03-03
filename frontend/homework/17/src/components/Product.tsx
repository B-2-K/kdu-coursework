import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/product.scss';

interface Product {
  id: number;
  title: string;
  price: number;
  description: string;
  category: string;
  image: string;
  rating: {
    rate: number;
    count: number;
  }
}

const Product: React.FC<{ product: Product }> = ({ product }) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/product/${product.id}`, { state: { product: product } });
  };

  return (
    <div className="product" onClick={handleClick}>
      <div className="product-img">
        <img src={product.image} alt="product-image" />
      </div>
      <div className="product-info">
        <p className="product-name">{product.title}</p>
        <p className="product-price">${product.price}</p>
      </div>
    </div>
  );
};

export default Product;
