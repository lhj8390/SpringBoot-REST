import { FrownFilled } from "@ant-design/icons";
import { Tag, Table, Result, Button } from "antd";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getOrderListAsync } from "../../actions/order";

const OrderList = () => {

    const dispatch = useDispatch();
    const { orderList } = useSelector(state => state.order);
    const { isAuthenticated } = useSelector(state => state.auth);

    useEffect(() => {
        if (isAuthenticated) {
            dispatch(getOrderListAsync());
        }
    }, [isAuthenticated]);

    const colorMap = {
        'WAIT': 'default',
        'PROCESSING': 'blue',
        'REJECTED': 'red',
        'COMPLETED': 'green'
    };

    const columes = [
        { title: 'ID', dataIndex: 'orderId', key: 'orderId', },
        { title: 'Name', dataIndex: 'productName', key: 'productName', },
        { title: 'Order Date', dataIndex: 'orderDt', key: 'orderDt', },
        { title: 'Price', dataIndex: 'price', key: 'price', },
        { title: 'Amount', dataIndex: 'amount', key: 'amount', },
        { title: 'State', dataIndex: 'state', key: 'state', render: (_, record) => (
            <>
                <Tag color={colorMap[record.state]} key={record.orderId}>{record.state}</Tag>
            </>
        )},
    ];

    const onLoginClick = () => navigate('/login');

    return (
        <>
            { isAuthenticated ?
                <Table
                    columns={columes}
                    rowKey={order => order.orderId}
                    dataSource={orderList}/>
            :
                <Result
                    icon={<FrownFilled />}
                    title='해당 페이지에 대한 권한이 없습니다.'
                    subTitle='로그인 후 이용해주세요.'
                    extra={<Button type='primary' onClick={onLoginClick}>로그인</Button>}
                />
            }
        </>
    );
};

export default OrderList;