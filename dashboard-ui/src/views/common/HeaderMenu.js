import React, { useEffect, useState } from "react";
import { Button, Col, Layout, Row } from 'antd';
import { LoginOutlined, LogoutOutlined } from '@ant-design/icons';
import { useLocation, useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
const { Header } = Layout;

const HeaderMenu = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { isAuthenticated } = useSelector(state => state.auth);
    const [path, setPath] = useState('home');
    useEffect(() => {

        if (location.pathname == '/') {
            setPath('home');
        }
        else if (location.pathname.includes('/product')) {
            setPath('상품 리스트');
        }
        else if (location.pathname.includes('/login')) {
            setPath('로그인');
        }
    }, [location.pathname])


    const onLoginClick = () => {
        navigate('/login');
    }

    return (
        <Header style={{ background: '#fff'}}>
            <Row justify="end">
                <Col flex='auto'><h2>{path}</h2></Col>
                <Col flex='50px'>
                    { isAuthenticated ?
                        <Button
                            icon={<LogoutOutlined />}>로그아웃
                        </Button>
                    :
                        <Button
                            icon={<LoginOutlined />}
                            onClick={onLoginClick}>
                                로그인
                        </Button>
                    }
                </Col>
            </Row>
        </Header>
    )
}

export default HeaderMenu;