import { combineReducers } from 'redux';
import product from './product';
import auth from './auth';
import order from './order';

const rootReducer = combineReducers({
	product,
	auth,
	order
});

export default rootReducer;