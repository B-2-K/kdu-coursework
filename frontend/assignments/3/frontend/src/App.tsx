import { BrowserRouter, Route, Routes } from 'react-router-dom'
import './App.css'
import Home from './components/dashboard/home/Home'
import Portfolio from './components/portfolio/Portfolio'
import Stock from './components/stock/Stock'
import { Summarizer } from './components/summarizer/Summarizer'

function App() {
  return (
    <div className="app">
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/portfolio" element={<Portfolio />} />
        <Route path="/summarizer" element={<Summarizer />} />
        <Route path="/stock/:stockSymbol" element={<Stock/>} />
      </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
