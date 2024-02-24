import '../styles/Main.scss';
import Item from './Product';

import React, { useState, useEffect } from 'react';

export default function Main({ search, filter, sort }) {
    const [products, setProducts] = useState([]);
    console.log('data fetched')
    useEffect(() => {
        fetch('https://fakestoreapi.com/products')
            .then(response => response.json())
            .then((data) => {
                setProducts(data);
                console.log('Fetched Products:', data);
                console.log('data fetched')
            })
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    const filteredProducts = products.filter(product => {
        const isBrandMatch = filter ? product.category.toLowerCase() === filter.toLowerCase() : true;
        const isNameMatch = search ? product.title.toLowerCase().includes(search.toLowerCase()) : true;
        return isBrandMatch && isNameMatch;
    });

    const sortedProducts = sort === 'ascending' ? filteredProducts.sort((a, b) => a.price - b.price) : sort === 'descending' ? filteredProducts.sort((a, b) => b.price - a.price) : filteredProducts;

    return (
        <div className="main">
            <h2>KDU <span className="blue-text">MARKETPLACE</span></h2>
            <div className="section">
                {sortedProducts.map((product, index) => (
                    <div key={index} className="products">
                        <Item product={product} />
                    </div>
                ))}
            </div>
        </div>
    );
}
