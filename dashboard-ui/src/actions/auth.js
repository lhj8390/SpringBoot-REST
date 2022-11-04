import { createAction } from '@reduxjs/toolkit';
import apiInstance from '../api/apiInstance';
import { LOGIN_FAILED, LOGIN_SUCCESS } from '../constants/actionTypes';

const loginSuccess = createAction(LOGIN_SUCCESS);
const loginFailed = createAction(LOGIN_FAILED);

export const loginAsync = (loginData) => {

	return async (dispatch) => {
		apiInstance().post('/api/auth/login', loginData)
		.then(response => {
            dispatch(loginSuccess(response.data.data.token));
        })
        .catch(e => {
            dispatch(loginFailed(e.response.data.message));
        });
	  };
};