import React, { useEffect } from 'react';
import { Navigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { logoutAsync } from '../../actions/auth';

const Logout = () => {

    const dispatch = useDispatch();
    useEffect(() => {
        dispatch(logoutAsync());
    }, []);

    return (
        <Navigate to='/login'/>
    )
}

export default Logout;