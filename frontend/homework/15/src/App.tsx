import TodoApp from './components/TodoApp';
import './App.scss';
import store from './redux/store';
import { Provider } from'react-redux';

function App() {
  return (
    <Provider store={store}>
    <TodoApp />
  </Provider>
  );
}

export default App;
