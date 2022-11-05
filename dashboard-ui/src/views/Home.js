import React from 'react';
import { useSelector } from 'react-redux';
import { Button, Result } from "antd";
import { FrownFilled } from "@ant-design/icons";
import { useNavigate } from 'react-router-dom';


const Home = () => {

    const { isAuthenticated } = useSelector(state => state.auth);
    const navigate = useNavigate();

    const onLoginClick = () => {
        navigate('/login');
    }

    return (
        <>
            { isAuthenticated ?
                <>home!</>
            :
                <Result
                    icon={<FrownFilled />}
                    title='해당 페이지에 대한 권한이 없습니다.'
                    subTitle='로그인 후 이용해주세요.'
                    extra={<Button type='primary' onClick={onLoginClick}>로그인</Button>}
            />
            }
        </>
    )
};

export default Home;