import { FrownFilled } from "@ant-design/icons";
import { Button, Image, message, Result, Space, Table } from "antd";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { deleteProductAsync, getProductListAsync } from "../../actions/product";
import ProductEdit from "./ProductEdit";

const productList = () => {

    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { productList, isSuccess, error } = useSelector(state => state.product);
    const { isAuthenticated } = useSelector(state => state.auth);
    const [ open, setOpen ] = useState(false);      // 모달 오픈 여부
    const [ product, setProduct ] = useState({});   // 모달 내부의 상세 값

    useEffect(() => {
        if (isAuthenticated) {
            dispatch(getProductListAsync());
        }
    }, [isAuthenticated]);

    useEffect(() => {
        if (isSuccess) {
            // 수정, 삭제가 성공할 때마다 갱신한다.
            dispatch(getProductListAsync());
            message.success('성공!');
        }

        if (error != null) {
            message.error(JSON.stringify(error));
        }

    }, [isSuccess, error]);

    const onLoginClick = () => navigate('/login');

    const onEditClick = (id) => {
        setProduct(productList.filter(p => p.id == id)[0]);
        setOpen(true);
    }

    const onDeleteClick = (id) => dispatch(deleteProductAsync(id));

    const columes = [
        {
            title: 'Image',
            key: 'thumnail',
            render: (_, record) => (
                <Image width={100} src={record.thumnail} />
            )
        },
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
            render: (_, record) => (
                <Space size='middle'>
                    <a onClick={() => onEditClick(record.id)}>수정</a>
                    <a onClick={() => onDeleteClick(record.id)}>삭제</a>
                </Space>
            )
        },
    ];

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
            { open && <ProductEdit open={open} setOpen={setOpen} product={product}/> }
        </>
    );
};

export default productList;