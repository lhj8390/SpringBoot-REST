import { createAction } from '@reduxjs/toolkit';
import apiInstance from '../api/apiInstance';
import { GET_ORDER_LIST_FAILED, GET_ORDER_LIST_SUCCESS } from '../constants/actionTypes';

const getOrderListSuccess = createAction(GET_ORDER_LIST_SUCCESS);
const getOrderListFailed = createAction(GET_ORDER_LIST_FAILED);

export const getOrderListAsync = () => {
    return async (dispatch) => {
        apiInstance().get('/api/order/')
		.then(response => {
            dispatch(getOrderListSuccess(response.data.data.content));
        })
        .catch(e => {
            dispatch(getOrderListFailed(e.response.data.data));
        });
    }
}