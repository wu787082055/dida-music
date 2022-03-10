/**
 * @Description: -TODO
 * @Author ZhangWei
 * @Date   2021/7/20 14:33
 * @Version V1.0
 */

import React, {useEffect, useRef, useState} from 'react';
import {Button, Form, Input, message, Modal, Select, Space} from "antd";
import companyApi from "../../http/api/CompanyApi";
import userApi from "../../http/api/UserApi";

const {Option} = Select;

const layout = {
    labelCol: {span: 4},
    wrapperCol: {span: 16},
};
const tailLayout = {
    wrapperCol: {offset: 8, span: 16},
};


export default function UserAddForm(props) {

    const [form] = Form.useForm();

    const formRef = useRef();


    const [companyList, setCompanyList] = useState([]);

    const [buttonLoading, setButtonLoading] = useState(false);

    async function initCompanyList() {
        let data = await companyApi.list();
        setCompanyList(data);
    }

    async function handleSummit() {
        //获取表单数据
        form.validateFields()
            .then(async (res) => {
                res.companyCode = 'root';
                setButtonLoading(true);
                await userApi.add(res);
                setButtonLoading(false);
                message.success('添加成功');
                props.finish();
            })
            .catch(error => {
                let {errorFields} = error;
                let errorMsg = errorFields[0].errors[0];
                message.error(errorMsg);
            })
    }


    useEffect(() => {
        initCompanyList()
    }, []);

    return (
        <Modal title={"添加员工"}
               visible={props.visible}
               onCancel={props.cancel}
               footer={null}
        >
            <Form
                form={form}
                ref={formRef}
                {...layout}>

                <Form.Item
                    name={'username'}
                    label='用户名'
                    rules={[
                        {
                            type: 'string',
                            message: '用户名不可以为空',
                            required: true
                        }
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name={'peopleCode'}
                    label='人员编码'
                    rules={[
                        {
                            type: 'string',
                            message: '人员编码不可以为空',
                            required: true
                        }
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name={'companyId'}
                    label='公司所属'
                    rules={[
                        {
                            type: 'string',
                            message: '公司所属不可以为空',
                            required: true
                        }
                    ]}
                >

                    <Select>
                        {
                            companyList.map(
                                item => (<Option value={item.id} key={item.id}>{item.company}</Option>)
                            )
                        }
                    </Select>

                </Form.Item>


                <Form.Item {...tailLayout}>
                    <Space>
                        <Button type="primary" htmlType="submit" onClick={handleSummit}
                                loading={buttonLoading}>提交</Button>
                        <Button type="default" htmlType="reset">
                            重置
                        </Button>
                    </Space>
                </Form.Item>

            </Form>
        </Modal>
    );
}

