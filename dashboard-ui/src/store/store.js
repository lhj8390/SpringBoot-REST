import { configureStore } from '@reduxjs/toolkit';
import rootReducer from '../reducers';
import thunk from 'redux-thunk';
import logger from 'redux-logger';


const middlewares = [thunk, logger];
// if (process.env.NODE_ENV === 'development') {
// 	middlewares.push(logger);
// }

const store = () => {

	return configureStore({
		reducer: rootReducer,
		middleware: [...middlewares]
	});
};

export default store;