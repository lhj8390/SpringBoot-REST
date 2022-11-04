import { Space, Table } from "antd";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
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
    const {productList} = useSelector(state => state.product);

    useEffect(() => {
        dispatch(getProductListAsync());

    }, [])

    return(
        <>
            <Table
                columns={columes}
                rowKey={product => product.id}
                dataSource={productList}/>
        </>
    );
};

export default productList;