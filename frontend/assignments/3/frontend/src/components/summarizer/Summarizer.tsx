import { useEffect, useState } from "react";
import worker from './worker/app.worker?worker';
import ClipLoader from "react-spinners/ClipLoader";
import Header from "../dashboard/header/Header";
import { toast } from "react-toastify";

import './Summarizer.scss';

const info = {};

interface ISummary {
    company: string;
    bestBuyDate: string;
    bestBuyPrice: number;
    bestSellDate: string;
    bestSellPrice: number;
    maxProfit: number;
  }

export function Summarizer() {
  const [stocksSummary, setStocksSummary] = useState<ISummary[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(false);

  useEffect(() => {
    const fetchDataWorker = async () => {
      const startTime = performance.now();
      const serviceWorker = new worker();
      serviceWorker.postMessage("getData");
      setIsLoading(true);
      serviceWorker.addEventListener("message", (event) => {
        setStocksSummary([
          ...event.data,
          {
            company: "Test",
            bestBuyDate: "",
            bestBuyPrice: 0,
            bestSellDate: "",
            bestSellPrice: 0,
            maxProfit: 0,
          },
        ]);
        const endTime = performance.now();
        const elapsedTime = endTime - startTime;
        console.log("Time taken by web worker (ms):", elapsedTime);
        setIsLoading(false);
      });
    };

    fetchDataWorker();
  }, []);

  return isLoading ? (
    <div style={{ textAlign: "center", marginTop: "5rem" }}>
      <ClipLoader
        color="black"
        loading={isLoading}
        size={100}
        aria-label="Loading Spinner"
        data-testid="loader"
        className="loader"
      />
    </div>
  ) : (
    <>
    <Header/>
    <div className="SummaryContainer">
      {stocksSummary.map((stock) => (
        <div key={stock.company} className={stock.maxProfit === 0 ? "Summary redContainer" : "Summary"}>
          <div>
            <div className="Company">{stock.company}</div>
            <div>Profit margin: &#8377;{stock.maxProfit}</div>
          </div>
          {stock.maxProfit > 0 && (
            <div className="RightContainer">
              <div className="StyledDate">
                <div>Buy: &#8377;{stock.bestBuyPrice} on &nbsp;</div>
                <div>{stock.bestBuyDate}</div>
              </div>
              <div className="StyledDate">
                <div>Sell: &#8377;{stock.bestSellPrice} on &nbsp;</div>
                <div>{stock.bestSellDate}</div>
              </div>
            </div>
          )}
        </div>
      ))}
    </div>
    {isLoading ? toast.error('error while fetching the summarizer') : toast.success('fetched the summarizer successfully')}
    </>
  );
}
