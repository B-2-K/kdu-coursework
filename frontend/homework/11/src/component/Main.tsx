import React, { useEffect, useState } from "react";
import "../styles/Main.scss";
import { APIQuote } from "../utils/ApiQuote";
import Quote from "./Quote";
import Filter from "./Filter";
import axios from 'axios';

function Main() {
    let [quotes, setQuotes] = useState<APIQuote[]>([]);

    const [filterArray, setFilterArray] = useState<string[]>([]);

    function addAQuote() {
        axios.get("https://api.quotable.io/quotes/random")
            .then((response) => {
                const data = response.data;
                setQuotes([...data, ...quotes]);
                console.log(quotes);
            })
            .catch((error) => {
                console.error("Error fetching new quote:", error);
            });
    }


    const handleDeleteFilter = (filterArray: string) => {
        setFilterArray((prevFilters) => prevFilters.filter((f) => f !== filterArray));
    };

    useEffect(() => {
        //this runs only once
        axios.get("https://api.quotable.io/quotes/random?limit=3")
            .then((response) => {
                setQuotes(response.data);
            });
        console.log("fetched 3", quotes);
    }, []);

    const filteredQuotes = quotes.filter(
        (quote) =>
            filterArray.length === 0 ||
            filterArray.some((tag) => quote.tags.includes(tag))
    );

    return (
        <div className="main-container">
            <button onClick={addAQuote} id="new-quote_button">NEW QUOTE</button>
            <Filter filters={filterArray} onDeleteFilter={handleDeleteFilter} />
            {filteredQuotes.map((quote) => {
                return (
                    <Quote setFilter={setFilterArray} key={quote._id} quote={quote} />
                );
            })}
        </div>
    );
}

export default Main;
