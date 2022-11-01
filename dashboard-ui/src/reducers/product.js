import {handleActions} from 'redux-actions';
import { GET_PRODUCT_LIST_SUCCESS } from '../constants/actionTypes';

const initialState = {
	productList: []
};

const product = handleActions({
	// [GET_PRODUCT_LIST_SUCCESS] : (state, action) => ({...state, productList: action.payload}),
    [GET_PRODUCT_LIST_SUCCESS] : (state, action) => {
        console.log(action)
        return {...state, productList: action.payload}
    },
}, initialState);

export default product;