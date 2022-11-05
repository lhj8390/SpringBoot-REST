import { Form, InputNumber, Input, Modal, Select, message } from "antd";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { resetAsync } from "../../actions/notification";
import { getProductListAsync, modifyProductAsync } from "../../actions/product";

const ProductEdit = props => {

    const { open, setOpen, product } = props;
    const [form] = Form.useForm();
    const dispatch = useDispatch();

    const handleEdit = () => {
        form
            .validateFields()
            .then((values) => {
                form.resetFields();
                values.id = product.id;
                dispatch(modifyProductAsync(values));
            })
            .catch((info) => {
                console.log('Validate Failed:', info);
            })
            .finally(() => setOpen(false));
    };

    const closeModal = () => setOpen(false);

    return (
        <>
            <Modal
                title='상품 수정'
                open={open}
                onOk={handleEdit}
                onCancel={closeModal}
            >
                <Form wrapperCol={{ span: 16 }} labelCol={{ span: 4 }}
                    form={form}
                >
                    <Form.Item
                        name="name"
                        label="Name"
                        initialValue={product.name}
                        rules={[{ required: true, message: '상품 명을 입력해주세요.'}]}>

                        <Input />
                    </Form.Item>
                    <Form.Item
                        name="category"
                        label="Category"
                        initialValue={product.category}
                        rules={[{ required: true, message: '카테고리를 선택해주세요.' }]}>

                        <Select>
                            <Select.Option value="ELECTRONIC">전자기기</Select.Option>
                            <Select.Option value="FASHION">패션</Select.Option>
                            <Select.Option value="BOOK">잡화</Select.Option>
                        </Select>
                    </Form.Item>
                    <Form.Item
                        name="price"
                        label="Price"
                        initialValue={product.price}
                        rules={[{ required: true, message: '가격을 입력해주세요.'}]}>

                        <InputNumber />
                    </Form.Item>
                    <Form.Item
                        name="amount"
                        label="amount"
                        initialValue={product.amount}
                        rules={[{ required: true, message: '상품량을 입력해주세요.'}]}>

                        <InputNumber />
                    </Form.Item>

                </Form>
            </Modal>
        </>
    )
}

export default ProductEdit;