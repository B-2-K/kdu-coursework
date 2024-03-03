// Home.js
import React, { useState } from 'react';
import Navbar from './Navbar';
import Main from './Main';
import '../styles/Home.scss';

export default function Home() {
    const [search, setSearch] = useState('');
    const [filter, setFilter] = useState('');
    const [sort, setSort] = useState('');

    return (
        <div className="home">
            <Navbar setSearch={setSearch} setFilter={setFilter} setSort={setSort} />
            <Main search={search} filter={filter} sort={sort} />
        </div>
    );
}
