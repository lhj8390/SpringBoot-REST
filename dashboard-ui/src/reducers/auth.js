import {handleActions} from 'redux-actions';
import { JOIN_FAILED, JOIN_SUCCESS, LOGIN, LOGIN_CHECK, LOGIN_FAILED, LOGIN_SUCCESS, LOGOUT, RESET } from '../constants/actionTypes';

const initialState = {
	isAuthenticated: false,
	token: null,
	error: null,
	isSuccess: false,
};

const auth = handleActions({
	[LOGIN] : (state) => ({...state, isAuthenticated: false, error: null}),
	[LOGIN_SUCCESS] : (state, action) => ({...state, isAuthenticated: true, token: action.payload}),
	[LOGIN_FAILED] : (state, action) => ({...state, error: action.payload}),
	[LOGOUT]: (state) => ({...state, isAuthenticated: false, token: null}),
	[LOGIN_CHECK] : (state, action) => ({...state, isAuthenticated: true, token: action.payload}),
	[RESET]: (state) => ({...state, error: null, isSuccess: false}),
	[JOIN_SUCCESS]: (state) => ({...state, isSuccess: true}),
	[JOIN_FAILED]: (state, action) => ({...state, error: action.payload}),
}, initialState);

export default auth;