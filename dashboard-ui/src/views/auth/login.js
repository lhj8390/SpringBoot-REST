import React, { useEffect } from "react";
import { Button, Form, Input, message } from 'antd';
import { useDispatch, useSelector } from "react-redux";
import { loginAsync } from "../../actions/auth";
import { useCookies } from 'react-cookie';
import { resetErrorAsync } from "../../actions/notification";
import { useNavigate } from "react-router-dom";

const Login = () => {

    const [form] = Form.useForm();
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const { token, error } = useSelector(state => state.auth);
    const [cookies, setCookie, removeCookie] = useCookies(['token']);

    const onFinish = (values) => {
        dispatch(loginAsync(values));
    };

    const onReset = () => {
        form.resetFields();
    };

    const onFill = () => {
        form.setFieldsValue({
            email: 'test@test.com',
            password: 'test1234',
        });
    };

    useEffect(() => {
        if (token != null) {
            message.success('로그인 성공!');
            setCookie('token', token);
            navigate('/');
        } else {
            removeCookie('token');
        }
    }, [token]);

    useEffect(() => {
        if (error != null) {
            message.error(error);
            dispatch(resetErrorAsync());
        }
    }, [error]);

    return (
        <>
          <Form wrapperCol={{ span: 8 }}
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

            <Form.Item wrapperCol={{ offset: 2, span: 8 }}>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
                <Button htmlType="button" onClick={onReset}>
                    Reset
                </Button>
                <Button type="link" htmlType="button" onClick={onFill}>
                    test 계정
                </Button>
            </Form.Item>
          </Form>
        </>
    )
}

export default Login;