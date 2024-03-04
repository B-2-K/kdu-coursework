import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.tsx'
import './index.css'
import { Provider } from 'react-redux'
import store from './redux/store.ts'
import { ToastContainer } from 'react-toastify'

ReactDOM.createRoot(document.getElementById('root')!).render(
    <Provider store={store}>
      <ToastContainer />
      <App />
    </Provider>
)
