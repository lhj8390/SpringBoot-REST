import {handleActions} from 'redux-actions';
import { LOGIN, LOGIN_CHECK, LOGIN_FAILED, LOGIN_SUCCESS, LOGOUT, RESET_ERROR } from '../constants/actionTypes';

const initialState = {
	isAuthenticated: false,
	token: null,
	error: null
};

const auth = handleActions({
	[LOGIN] : (state) => ({...state, isAuthenticated: false, error: null}),
	[LOGIN_SUCCESS] : (state, action) => ({...state, isAuthenticated: true, token: action.payload}),
	[LOGIN_FAILED] : (state, action) => ({...state, error: action.payload}),
	[LOGOUT]: (state) => ({...state, isAuthenticated: false, token: null}),
	[LOGIN_CHECK] : (state, action) => ({...state, isAuthenticated: true, token: action.payload}),
	[RESET_ERROR]: (state) => ({...state, error: null}),
}, initialState);

export default auth;