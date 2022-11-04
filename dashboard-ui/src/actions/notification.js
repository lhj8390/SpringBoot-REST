import { createAction } from '@reduxjs/toolkit';
import { RESET_ERROR } from '../constants/actionTypes';

const resetError = createAction(RESET_ERROR);

export const resetErrorAsync = () => {
    return resetError();
}