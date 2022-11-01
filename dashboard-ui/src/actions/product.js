import { createAction } from '@reduxjs/toolkit';
import apiInstance from '../api/apiInstance';
import { GET_PRODUCT_LIST_SUCCESS } from '../constants/actionTypes';


const getProductListSuccess = createAction(GET_PRODUCT_LIST_SUCCESS);


export const getProductListAsync = () => {
	return async function (dispatch) {
		apiInstance().get('/api/product/')
		.then(response => dispatch(getProductListSuccess(response.data.data.content)));
	  }
}