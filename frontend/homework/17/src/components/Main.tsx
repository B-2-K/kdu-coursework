import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { AppDispatch, RootState } from '../redux/store';
import { fetchProducts } from '../redux/fetchProducts';
import '../styles/Main.scss';
import Product from './Product';


interface MainProps {
    search?: string;
    filter?: string;
    sort?: 'ascending' | 'descending';
}

const Main: React.FC<MainProps> = () => {
    const dispatch = useDispatch<AppDispatch>();

    const { products, loading, error } = useSelector((state: RootState) => state.products);

    useEffect(() => {
        dispatch(fetchProducts());
    }, [dispatch]);

    const searchValue = useSelector((state: RootState) => state.search.search);

    const filterValue = useSelector((state: RootState) => state.filter.filter);

    const sortValue = useSelector((state: RootState) => state.sort.sort);

    if (loading) {
        return <div className="loader"></div>;
    }

    if (error) {
        return <div>Error : {error}</div>
    }

    const filteredProducts = products.filter(product => {
        const isBrandMatch = filterValue ? product.category.toLowerCase() === filterValue.toLowerCase() : true;
        const isNameMatch = searchValue ? product.title.toLowerCase().includes(searchValue.toLowerCase()) : true;
        return isBrandMatch && isNameMatch;
    });

    const sortedProducts = sortValue === 'ascending' ? filteredProducts.sort((a, b) => a.price - b.price) :
        sortValue === 'descending' ? filteredProducts.sort((a, b) => b.price - a.price) :
            filteredProducts;

    return (
        <div className="main">
            <h2>KDU <span className="blue-text">MARKETPLACE</span></h2>
            <div className="section">
                {sortedProducts.map((product, index) => (
                    <div key={index} className="products">
                        <Product product={product} />
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Main;
