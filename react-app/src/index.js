// React Dependancies
import React from 'react';
import ReactDOM from 'react-dom';

// Redux Dependancies
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import thunk from "redux-thunk";
import reducer from './store/reducers/reducer';

// Custom React Components
import Routes from './pages/Routes';
import Page from './pages/common/Page';

// Styles
import './styles/index.scss';
import 'bulma/css/bulma.min.css';
import "react-toastify/dist/ReactToastify.css";

import * as serviceWorker from './serviceWorker';

const store = createStore(reducer, applyMiddleware(thunk));


ReactDOM.render(<Provider store={store}><Page><Routes/></Page></Provider>, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: http://bit.ly/CRA-PWA
serviceWorker.unregister();
