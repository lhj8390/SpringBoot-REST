import React, { useEffect } from 'react';
import "antd/dist/antd.css";
import { Routes, Route } from "react-router-dom";
import { Layout } from 'antd';
import ProductList from './views/product/ProductList';
import HeaderMenu from './views/common/HeaderMenu';
import SideMenu from './views/common/SideMenu';
import Home from './views/Home';
import Login from './views/auth/login';
import Logout from './views/auth/logout';
import { useDispatch } from 'react-redux';
import { useCookies } from 'react-cookie';
import { loginCheckAsync } from './actions/auth';
import Join from './views/auth/join';
import OrderList from './views/order/OrderList';
const { Content, Footer } = Layout;

const App = () => {

	const dispatch = useDispatch();
	const [ cookies ] = useCookies(['token']);

	useEffect(() => {
		dispatch(loginCheckAsync(cookies.token));
	}, [cookies.token]);

	return (
		<Layout className="App" style={{ minHeight: '100vh' }}>
			<SideMenu/>
			<Layout>
				<HeaderMenu />
				<Content style={{ padding: '24px'}}>
					<div style={{ background: '#fff', padding: '24px', minHeight: '60vh' }}>
						<Routes>
							<Route exact path='/' element={<Home/>}/>
							<Route exact path='/login' element={<Login/>}/>
							<Route exact path='/join' element={<Join/>}/>
							<Route exact path='/logout' element={<Logout />}/>
							<Route exact path='/order' element={<OrderList />}/>
							<Route exact path='/product' element={<ProductList/>}/>
						</Routes>
					</div>
				</Content>
				<Footer style={{ textAlign: 'center' }}>©2022 Created by lhj8390</Footer>
			</Layout>
		</Layout>
	);
}

export default App;
