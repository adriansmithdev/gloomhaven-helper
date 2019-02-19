import React from 'react';
import ReactDOM from 'react-dom';
import './styles/index.scss';
import 'bulma/css/bulma.min.css';
import Routes from './pages/Routes';
import * as serviceWorker from './serviceWorker';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import thunk from "redux-thunk";
import reducer from './store/reducers/reducer';

const store = createStore(reducer, applyMiddleware(thunk));



ReactDOM.render(<Provider store={store}><Routes/></Provider>, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
