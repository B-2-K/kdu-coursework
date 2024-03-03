import '../styles/Main.scss';
import Item from './Product';
import { products } from '../data/data'

export default function Main({ search, filter, sort }) {
    const filteredProducts = products.filter(product => {
        const isBrandMatch = filter ? product.madeBy.toLowerCase() === filter.toLowerCase() : true;
        const isNameMatch = search ? product.name.toLowerCase().includes(search.toLowerCase()) : true;
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
