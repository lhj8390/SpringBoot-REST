import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Badge, Button, Calendar, Result, Statistic } from "antd";
import { FrownFilled } from "@ant-design/icons";
import { useNavigate } from 'react-router-dom';
import moment from 'moment';
import { getOrderListAsync } from '../actions/order';


const Home = () => {

    const dispatch = useDispatch();
    const { isAuthenticated } = useSelector(state => state.auth);
    const { orderList } = useSelector(state => state.order);
    const navigate = useNavigate();

    useEffect(() => {
        dispatch(getOrderListAsync());
    }, []);

    // 날짜에 해당하는 데이터 받아오기
    const getListData = (value) => {
        const listData = [];
        const colorMap = {
            'WAIT': 'default',
            'PROCESSING': 'blue',
            'REJECTED': 'red',
            'COMPLETED': 'green'
        };
        orderList.map(order => {
            const orderDt = moment(order.orderDt);
            if (moment(value).date() == (orderDt).date() &&
                moment(value).month() == orderDt.month()) {
                listData.push({
                    type: colorMap[order.state],
                    content: order.productName
                });
            }
        });
        return listData;
    };

    // 월에 해당하는 데이터 받아오기
    const getMonthData = (value) => {
        const monthData = [];

        orderList.map(order => {
            const orderDt = moment(order.orderDt);
            if (moment(value).month() == orderDt.month()) {
                monthData.push(order);
            }
        });
        return monthData.length;
      };

    const dateCellRender = (value) => {
        const listData = getListData(value);
        return (
          <ul style={{ margin: 0, padding: 0, listStyle: 'none' }}>
            {listData.map((item) => (
              <li key={item.content}>
                <Badge status={item.type} text={item.content} />
              </li>
            ))}
          </ul>
        );
    };

    const monthCellRender = (value) => {
        const num = getMonthData(value);
        return num ? (
            <Statistic title='총 주문량' value={num} />
        ) : null;
    };

    const onLoginClick = () => navigate('/login');

    return (
        <>
            { isAuthenticated ?
                <>
                    <Calendar
                        dateCellRender={dateCellRender}
                        monthCellRender={monthCellRender} />
                </>
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