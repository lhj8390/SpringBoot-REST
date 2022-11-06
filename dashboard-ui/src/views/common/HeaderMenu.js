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

        setPath(findPath(location.pathname));

    }, [location.pathname])

    const findPath = (path) => {
        const pathColumn = {
            "/" : "home",
            "/product": "상품 리스트",
            "/login": "로그인",
            "/join": "회원가입",
            "/order": "주문 내역"
        };
        return pathColumn[path] || '페이지 없음';
    }

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