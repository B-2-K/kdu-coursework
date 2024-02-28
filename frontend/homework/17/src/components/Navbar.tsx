import { useDispatch } from 'react-redux';
import '../styles/Navbar.scss'
import { setSearch } from '../redux/searchSlice';
import { setFilter } from '../redux/filterSlice';
import { setSort } from '../redux/sortSlice';

export default function Navbar() {
    const dispatch = useDispatch();

    function handleSearch(event: React.ChangeEvent<HTMLInputElement>) {
        event.preventDefault();
        dispatch(setSearch(event.target.value));
    }

    function handleFilter(event: React.ChangeEvent<HTMLInputElement>) {
        event.preventDefault();
        dispatch(setFilter(event.target.value));
    }

    function handleSort(event: React.ChangeEvent<HTMLInputElement>) {
        event.preventDefault();
        dispatch(setSort(event.target.value));
    }

    return (
        <div className="navbar">
            <div className="nav-left">
                <input type="text" placeholder="Search.." onChange={handleSearch} />
                <button type="submit"><i className="fa fa-search"></i></button>
            </div>

            <div className="nav-right">
                <div className="filter">
                    <label htmlFor="brand">Filter : </label>
                    <select id="brand" onChange={handleFilter}>
                        <option value="" disabled selected>Category</option>
                        <option value="men's clothing">Men's Clothing</option>
                        <option value="women's clothing">Women's Clothing</option>
                        <option value="electronics">Electronics</option>
                        <option value="jewelery">Jewelery</option>
                    </select>
                </div>

                <div className="sort">
                    <label htmlFor="price">Sort : </label>
                    <select id="price" onChange={handleSort}>
                        <option value="" disabled selected>Price</option>
                        <option value="ascending">ASC</option>
                        <option value="descending">DESC</option>
                    </select>
                </div>
            </div>
        </div>
    )
}
