import React, { useEffect, useState } from "react";
import { Col, Layout, Row } from 'antd';
import { useLocation } from "react-router-dom";
const { Header } = Layout;

const HeaderMenu = () => {
    const location = useLocation();
    const [path, setPath] = useState('home')
    useEffect(() => {

        if (location.pathname == '/') {
            setPath('home');
        }
        else if (location.pathname.includes('/product')) {
            setPath('상품 리스트');
        }
    }, [location.pathname])

    return (
        <Header style={{ background: '#fff'}}>
            <Row justify="end">
                <Col flex='100px'><h2>{path}</h2></Col>
                <Col flex='auto'></Col>
            </Row>
        </Header>
    )
}

export default HeaderMenu;