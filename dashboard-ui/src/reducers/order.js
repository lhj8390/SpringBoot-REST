import { handleActions } from 'redux-actions';
import { GET_ORDER_LIST_FAILED, GET_ORDER_LIST_SUCCESS } from '../constants/actionTypes';


const initialState = {
	orderList : [],
    error : null,
};

const order = handleActions({
    [GET_ORDER_LIST_SUCCESS] : (state, action) => ({...state, orderList: action.payload }),
    [GET_ORDER_LIST_FAILED] : (state, action) => ({...state, error: action.payload }),

}, initialState);

export default order;