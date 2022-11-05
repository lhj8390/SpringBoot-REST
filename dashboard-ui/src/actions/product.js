import { createAction } from '@reduxjs/toolkit';
import apiInstance from '../api/apiInstance';
import { DELETE_PRODUCT_FAILED, DELETE_PRODUCT_SUCCESS, GET_PRODUCT_LIST_FAILED,
	GET_PRODUCT_LIST_SUCCESS, MODIFY_PRODUCT_FAILED, MODIFY_PRODUCT_SUCCESS } from '../constants/actionTypes';


const getProductListSuccess = createAction(GET_PRODUCT_LIST_SUCCESS);
const getProductListFailed = createAction(GET_PRODUCT_LIST_FAILED);
const modefiyProductSuccess = createAction(MODIFY_PRODUCT_SUCCESS);
const modefiyProductFailed = createAction(MODIFY_PRODUCT_FAILED);
const deleteProductSuccess = createAction(DELETE_PRODUCT_SUCCESS);
const deleteProductFailed = createAction(DELETE_PRODUCT_FAILED);


export const getProductListAsync = () => {
	return async (dispatch) => {
		apiInstance().get('/api/product/')
		.then(response => dispatch(getProductListSuccess(response.data.data.content)))
		.catch(e => {
            dispatch(getProductListFailed(e.response.data));
        });
	}
};

export const modifyProductAsync = (productData) => {
	return async (dispatch) => {
		apiInstance().put(`/api/product/${productData.id}/`, productData)
		.then(() => dispatch(modefiyProductSuccess()))
		.catch(e => dispatch(modefiyProductFailed(e.response.data.data)));
	}
}

export const deleteProductAsync = (id) => {
	return async (dispatch) => {
		apiInstance().delete(`/api/product/${id}/`)
		.then(() => dispatch(deleteProductSuccess()))
		.catch(e => dispatch(deleteProductFailed(e.response.data.data)));
	}
}