import { FrownFilled } from "@ant-design/icons";
import { Button, Result, Space, Table } from "antd";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { getProductListAsync } from "../../actions/product";

const columes = [
    {
        title: 'Name',
        dataIndex: 'name',
        key: 'name',
    },
    {
        title: 'Category',
        dataIndex: 'category',
        key: 'category',
    },
    {
        title: 'Price',
        dataIndex: 'price',
        key: 'price',
    },
    {
        title: 'Amount',
        dataIndex: 'amount',
        key: 'amount',
    },
    {
        title: 'Action',
        key: 'action',
        render: (_) => (
            <Space size='middle'>
                <a>수정</a>
                <a>삭제</a>
            </Space>
        )
    },
];

const productList = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { productList } = useSelector(state => state.product);
    const { isAuthenticated } = useSelector(state => state.auth);

    useEffect(() => {
        if (isAuthenticated) {
            dispatch(getProductListAsync());
        }

    }, [isAuthenticated]);

    const onLoginClick = () => {
        navigate('/login');
    }

    return(
        <>
            { isAuthenticated ?
                <Table
                    columns={columes}
                    rowKey={product => product.id}
                    dataSource={productList}/>
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

export default productList;