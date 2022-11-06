import React from 'react';
import { Layout, Menu } from 'antd';
import { AppstoreAddOutlined, HomeFilled, ShoppingCartOutlined } from '@ant-design/icons';
import { useNavigate } from 'react-router-dom';
const { Sider } = Layout;

const SideMenu = () => {

    const navigate = useNavigate();
    const onClick = (e) => {
        navigate(e.key);
    }
    return (
        <>
        <Sider collapsed={true} width='300'>
            <Menu
                theme='dark'
                mode='inline'
                defaultSelectedKeys={['/']}
                selectedKeys={location.pathname}
                onClick={onClick}
                items={[
                    {
                        key: '/',
                        icon: <HomeFilled />,
                        label: 'home'
                    },
                    {
                        key: '/product',
                        icon: <AppstoreAddOutlined />,
                        label: 'product'
                    },
                    {
                        key: '/order',
                        icon: <ShoppingCartOutlined />,
                        label: 'order'
                    },
                ]}
            >
            </Menu>
        </Sider>
        </>
    )

}

export default SideMenu;