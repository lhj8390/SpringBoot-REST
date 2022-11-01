import {applyMiddleware, compose} from 'redux';
import { configureStore } from '@reduxjs/toolkit';
import rootReducer from '../reducers';
import thunk from 'redux-thunk';
import {createLogger} from 'redux-logger';
import {composeWithDevTools} from 'redux-devtools-extension';

const logger = createLogger({
	level: 'info',
	collapsed: true,
});
let composeEnhancers = compose;

const middlewares = [thunk, logger];
// if (process.env.NODE_ENV === 'development') {
// 	middlewares.push(logger);
// 	composeEnhancers = composeWithDevTools;
// }

const store = () => {
	const initialState = {};

	return configureStore(
		{reducer: rootReducer},
		initialState,
		composeWithDevTools(applyMiddleware(...middlewares))
	);
};

export default store;