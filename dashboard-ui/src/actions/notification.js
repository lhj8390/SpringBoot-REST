import { createAction } from '@reduxjs/toolkit';
import { RESET } from '../constants/actionTypes';

const reset = createAction(RESET);

export const resetAsync = () => {
    return reset();
}