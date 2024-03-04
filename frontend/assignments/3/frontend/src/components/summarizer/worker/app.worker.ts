interface IData {
    date: string;
    prices: number[];
  }

interface ICompanyStock {
    company: string;
    symbol: string;
    data: IData[];
  }
  
 interface ISummary {
    company: string;
    bestBuyDate: string;
    bestBuyPrice: number;
    bestSellDate: string;
    bestSellPrice: number;
    maxProfit: number;
  }

const findMaxProfit = (stockData: ICompanyStock): [number, string, number, string, number]  => {
    let maxProfit = 0;
    let bestBuyDate = '';
    let bestBuyPrice = 0;
    let bestSellDate = '';
    let bestSellPrice = 0;
    let minPrice = Infinity;
    let buyDate = '';

    for (const dayData of stockData.data) {
        const { date, prices } = dayData;

        for (const price of prices) {
            if (price < minPrice) {
                minPrice = price;
                buyDate = date;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
                bestBuyDate = buyDate;
                bestBuyPrice = minPrice;
                bestSellDate = date;
                bestSellPrice = price;
            }
        }
    }
    
    return [maxProfit, bestBuyDate, bestBuyPrice, bestSellDate, bestSellPrice];
}

export async function fetchData(): Promise<ISummary[]> {
  try {
    const response = await fetch('https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/all-stocks-transactions.json')
    const data: ICompanyStock[] = await response.json();

    const summaryData: ISummary[] = [];

    for (const stock of data) {
        const { company } = stock;
        const [maxProfit, bestBuyDate, bestBuyPrice, bestSellDate, bestSellPrice] = findMaxProfit(stock);

        if (maxProfit > 0) {
            summaryData.push({
                company,
                bestBuyDate,
                bestSellDate,
                bestBuyPrice,
                bestSellPrice,
                maxProfit,
            });
        }
    }

    return summaryData;
  } catch (error) {
    console.error('Failed to fetch data:', error);
    return [];
  }
}
  

self.addEventListener('message', (event) => {
  const action = event.data;
  
  if (event.origin == self.origin) {
    console.warn('Received message from unexpected origin:', event.origin);
    return;
  }

  if (action === 'getData') {
    fetchData()
      .then((data) => {
        self.postMessage(data);
      })
      .catch((error) => {
        console.error('Failed to fetch data:', error);
        self.postMessage([]);
      });
  }
});