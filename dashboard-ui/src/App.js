import React from 'react';
import "antd/dist/antd.css";
import { Routes, Route } from "react-router-dom";
import { Layout } from 'antd';
import ProductList from './views/product/ProductList';
import HeaderMenu from './views/common/HeaderMenu';
import SideMenu from './views/common/SideMenu';
import Home from './views/Home';
const { Content, Footer } = Layout;

const App = () => {
	return (
		<Layout className="App" style={{ minHeight: '100vh' }}>
			<SideMenu/>
			<Layout>
				<HeaderMenu />
				<Content style={{ padding: '24px'}}>
					<div style={{ background: '#fff', padding: '24px', minHeight: '60vh' }}>
						<Routes>
							<Route exact path='/' element={<Home/>}/>
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
