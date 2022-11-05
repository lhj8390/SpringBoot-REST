import {handleActions} from 'redux-actions';
import { GET_PRODUCT_LIST_SUCCESS, RESET, LOGOUT } from '../constants/actionTypes';

const initialState = {
	productList: [],
    error: null
};

const product = handleActions({
	[GET_PRODUCT_LIST_SUCCESS] : (state, action) => ({...state, productList: action.payload}),
    [LOGOUT]: (state) => ({...state, productList: null}),
    [RESET]: (state) => ({...state, error: null}),
}, initialState);

export default product;