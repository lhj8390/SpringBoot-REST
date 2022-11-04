import {handleActions} from 'redux-actions';
import { GET_PRODUCT_LIST_SUCCESS, RESET_ERROR } from '../constants/actionTypes';

const initialState = {
	productList: [],
    error: null
};

const product = handleActions({
	[GET_PRODUCT_LIST_SUCCESS] : (state, action) => ({...state, productList: action.payload}),
    [RESET_ERROR]: (state) => ({...state, error: null}),
}, initialState);

export default product;