import '../styles/Navbar.scss'

export default function Navbar({ setSearch, setFilter, setSort }) {

    function handleSearch(event) {
        setSearch(event.target.value);
    }

    function handleFilter(event) {
        setFilter(event.target.value);
    }

    function handleSort(event) {
        setSort(event.target.value);
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
                        <option value="" disabled selected>Brand</option>
                        <option value="Samsung">Samsung</option>
                        <option value="Apple">Apple</option>
                        <option value="Google">Google</option>
                        <option value="Asus">Asus</option>
                        <option value="OnePlus">OnePlus</option>
                        <option value="Xiaomi">Xiaomi</option>
                        <option value="Huawei">Huawei</option>
                        <option value="Motorola">Motorola</option>
                        <option value="Sony">Sony</option>
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
