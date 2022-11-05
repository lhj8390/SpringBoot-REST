import { createAction } from '@reduxjs/toolkit';
import apiInstance from '../api/apiInstance';
import { JOIN_FAILED, JOIN_SUCCESS, LOGIN_CHECK_FAILED, LOGIN_CHECK_SUCCESS,
    LOGIN_FAILED, LOGIN_SUCCESS, LOGOUT } from '../constants/actionTypes';

const loginSuccess = createAction(LOGIN_SUCCESS);
const loginFailed = createAction(LOGIN_FAILED);
const logout = createAction(LOGOUT);
const loginCheckSuccess = createAction(LOGIN_CHECK_SUCCESS);
const loginCheckFailed = createAction(LOGIN_CHECK_FAILED);
const joinSuccess = createAction(JOIN_SUCCESS);
const joinFailed = createAction(JOIN_FAILED);

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
    if (token != null) {
        return loginCheckSuccess(token);
    }
    return loginCheckFailed();
}

export const joinAsync = (joinData) => {
    return async (dispatch) => {
        apiInstance().post('/api/auth/join', joinData)
        .then(response => {
            dispatch(joinSuccess());
        })
        .catch(e => {
            dispatch(joinFailed(e.response.data.data));
        });
    };
};
