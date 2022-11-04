import { createAction } from '@reduxjs/toolkit';
import apiInstance from '../api/apiInstance';
import { LOGIN, LOGIN_CHECK, LOGIN_FAILED, LOGIN_SUCCESS, LOGOUT } from '../constants/actionTypes';

const loginSuccess = createAction(LOGIN_SUCCESS);
const loginFailed = createAction(LOGIN_FAILED);
const logout = createAction(LOGOUT);
const loginCheck = createAction(LOGIN_CHECK);

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

export const logoutAsync = () => {
    return logout();
}

export const loginCheckAsync = (token) => {
    return loginCheck(token);
}
