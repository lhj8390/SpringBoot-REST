import { combineReducers } from 'redux';
import product from './product';
import auth from './auth';

const rootReducer = combineReducers({
	product,
	auth
});

export default rootReducer;