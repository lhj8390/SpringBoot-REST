import {handleActions} from 'redux-actions';
import { JOIN_FAILED, JOIN_SUCCESS, LOGIN, LOGIN_CHECK_SUCCESS,
	LOGIN_CHECK_FAILED, LOGIN_FAILED, LOGIN_SUCCESS, LOGOUT, RESET } from '../constants/actionTypes';

const initialState = {
	isAuthenticated: false,
	token: null,
	error: null,
	isSuccess: false,
};

const auth = handleActions({
	[LOGIN] : (state) => ({...state, isAuthenticated: false, error: null}),
	[LOGIN_SUCCESS] : (state, action) => ({...state, isAuthenticated: true, token: action.payload}),
	[LOGIN_FAILED] : (state, action) => ({...state, error: action.payload, isAuthenticated: false}),
	[LOGOUT]: (state) => ({...state, isAuthenticated: false, token: null}),
	[LOGIN_CHECK_SUCCESS] : (state, action) => ({...state, isAuthenticated: true, token: action.payload}),
	[LOGIN_CHECK_FAILED] : (state) => ({...state, isAuthenticated: false, token: null}),
	[RESET]: (state) => ({...state, error: null, isSuccess: false}),
	[JOIN_SUCCESS]: (state) => ({...state, isSuccess: true}),
	[JOIN_FAILED]: (state, action) => ({...state, error: action.payload}),
}, initialState);

export default auth;