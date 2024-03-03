import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Home from './components/Home'
import NotFoundPage from './components/NotFoundPage'
import ProductDetails from './components/ProductDetails'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/product/*" element={<ProductDetails />} />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
