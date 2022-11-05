import React, { useEffect, useState } from "react";
import { Button, Col, Layout, Row } from 'antd';
import { LoginOutlined, LogoutOutlined, UserAddOutlined } from '@ant-design/icons';
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
        else if (location.pathname.includes('/join')) {
            setPath('회원가입');
        }
    }, [location.pathname])


    const onLoginClick = () => {
        navigate('/login');
    }

    const onLogoutClick = () => {
        navigate('/logout');
    }

    const onJoinClick = () => {
        navigate('/join');
    }

    return (
        <Header style={{ background: '#fff'}}>
            <Row justify="end">
                <Col flex='auto'><h2>{path}</h2></Col>
                <Col flex='200px' style={{ textAlign: 'right' }}>
                    { isAuthenticated ?
                        <Button
                            icon={<LogoutOutlined />}
                            onClick={onLogoutClick}
                        >
                            로그아웃
                        </Button>
                    :
                        <>
                            <Button
                                icon={<LoginOutlined />}
                                onClick={onLoginClick}
                            >
                                로그인
                            </Button>
                            <Button
                                icon={<UserAddOutlined />}
                                onClick={onJoinClick}
                            >
                                회원가입
                            </Button>
                        </>
                    }
                </Col>
            </Row>
        </Header>
    )
}

export default HeaderMenu;