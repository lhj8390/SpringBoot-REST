import {handleActions} from 'redux-actions';
import { GET_PRODUCT_LIST_SUCCESS, RESET, LOGOUT, GET_PRODUCT_LIST_FAILED,
    MODIFY_PRODUCT_SUCCESS, MODIFY_PRODUCT_FAILED, DELETE_PRODUCT_SUCCESS, DELETE_PRODUCT_FAILED } from '../constants/actionTypes';

const initialState = {
	productList: [],
    isSuccess: false,
    error: null
};

const product = handleActions({
	[GET_PRODUCT_LIST_SUCCESS] : (state, action) => ({...state, productList: action.payload, error: null, isSuccess: false}),
    [GET_PRODUCT_LIST_FAILED] : (state, action) => ({...state, error: action.payload}),
    [LOGOUT]: (state) => ({...state, productList: null}),
    [MODIFY_PRODUCT_SUCCESS]: (state) => ({...state, isSuccess: true}),
    [MODIFY_PRODUCT_FAILED]: (state, action) => ({...state, error: action.payload}),
    [DELETE_PRODUCT_SUCCESS]: (state) => ({...state, isSuccess: true}),
    [DELETE_PRODUCT_FAILED]: (state, action) => ({...state, error: action.payload}),
    [RESET]: (state) => ({...state, error: null, isSuccess: false}),
}, initialState);

export default product;