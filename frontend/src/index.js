import 'babel-polyfill';

import React from 'react'
import {render} from "react-dom";
import { Provider } from 'react-redux'
import {syncHistoryWithStore, routerMiddleware, routerReducer} from "react-router-redux";
import {Router, Route, hashHistory} from "react-router";
import App from "./components/App";
import createSagaMiddleware from "redux-saga";
import {createStore, applyMiddleware, combineReducers} from "redux";
import reducers from "./reducers/rootReducer";
import rootSaga from './sagas/rootSaga'
import css from './app.less';

export const sagaMiddleware = createSagaMiddleware();
export const store = createStore(
    reducers,
    applyMiddleware(
        routerMiddleware(hashHistory),
        sagaMiddleware
    )
);
sagaMiddleware.run(rootSaga);

export const history = syncHistoryWithStore(hashHistory, store);

render(
    <Provider store={ store }>
        <Router history={ history }>
            <Route path="/" component={ App }>
            </Route>
        </Router>
    </Provider>,
    document.getElementById('app')
);
