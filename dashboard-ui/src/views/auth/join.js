import { Form, Input, Button, message } from "antd";
import React, { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { joinAsync } from "../../actions/auth";
import { resetAsync } from "../../actions/notification";

const Join = () => {

    const [form] = Form.useForm();
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { error, isSuccess } = useSelector(state => state.auth);

    const passwordValue = Form.useWatch('password', form);

    useEffect(() => {
        if (error != null) {
            message.error(JSON.stringify(error));
        }
        if (isSuccess) {
            message.success('회원가입 성공!');
            navigate('/login');
        }
        dispatch(resetAsync());
    }, [error, isSuccess]);

    const onReset = () => form.resetFields();
    const onFinish = (values) => {
        values.role = 'ROLE_ADMIN';
        dispatch(joinAsync(values));
    };


    return (
        <>
            <Form wrapperCol={{ span: 8 }} labelCol={{ span: 4 }}
                form={form}
                onFinish={onFinish}
            >
                <Form.Item
                    name="email"
                    label="ID (Email) "
                    rules={[{ required: true, message: '이메일을 입력해주세요.'}]}>

                    <Input />
                </Form.Item>
                <Form.Item
                    name="password"
                    label="Password"
                    rules={[{ required: true, message: '비밀번호를 입력해주세요.' }]}>

                    <Input.Password />
                </Form.Item>
                <Form.Item
                    name="password confirm"
                    label="Password confirm"
                    rules={[{ required: true, message: '비밀번호를 확인해주세요.'}, {
                        validator: (_, value) => {
                            if (passwordValue != value) {
                                return Promise.reject(new Error('비밀번호가 다릅니다.'));
                            }
                            return Promise.resolve();
                        }
                    }]}>

                    <Input.Password />
                </Form.Item>
                <Form.Item
                    name="username"
                    label="username"
                    rules={[{ required: true, message: '사용자 이름을 입력해주세요.'}]}>

                    <Input />
                </Form.Item>

                <Form.Item wrapperCol={{ offset: 4, span: 8 }}>
                    <Button type="primary" htmlType="submit">
                        회원가입
                    </Button>
                    <Button htmlType="button" onClick={onReset}>
                        Reset
                    </Button>
                </Form.Item>
            </Form>
        </>
    )
};

export default Join;