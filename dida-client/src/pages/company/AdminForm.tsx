/**
 * @Description: -TODO
 * @Author ZhangWei
 * @Date   2021/7/1 22:30
 * @Version V1.0
 */
import React, {createRef, useEffect, useRef, useState} from 'react';
import {Button, Form, Input, message, Modal, Space} from "antd";
import {FormInstance} from "antd/es/form";
import companyApi from "../../http/api/CompanyApi";
import {CompanyEntity} from "../../entity";

interface IProps {
    visible: boolean,
    formData?: any,
    cancel: () => void,
    finish: () => void
}

const layout = {
    labelCol: {span: 4},
    wrapperCol: {span: 16},
};
const tailLayout = {
    wrapperCol: {offset: 8, span: 16},
};

export default function AdminForm(props: IProps) {

    const [form] = Form.useForm();

    const formRef = useRef<FormInstance>();

    const [buttonLoading, setButtonLoading] = useState(false);


    useEffect(() => {
        let {code, company} = props.formData;
        form.setFieldsValue({
            company,
            code
        })
    }, props.formData);

    function handleSummit() {

        //console.log(props.formData);


        form.validateFields().then(async (res) => {
            setButtonLoading(true);
            res.id = props.formData.id;
            let api = res.id === undefined ? companyApi.add : companyApi.edit;
            await api(res);

            message.success("保存成功");
            setTimeout(() => {
                props.finish();
                setButtonLoading(false);
                form.resetFields();
            }, 500);

        }).catch((errorObj: any) => {
            let {errorFields} = errorObj;
            let errorMsg = errorFields[0].errors[0];
            message.error(errorMsg);
        })
    }


    return (
        <Modal title={"添加公司"}
               visible={props.visible}
               onCancel={props.cancel}
               footer={null}
        >

            <Form
                form={form}
                // @ts-ignore
                ref={formRef}
                {...layout}
            >

                <Form.Item
                    name={'company'}
                    label='公司名'
                    rules={[
                        {
                            type: 'string',
                            message: '公司名不可以为空',
                            required: true
                        }
                    ]}
                >
                    <Input/>
                </Form.Item>

                <Form.Item
                    name={'code'}
                    label='企业代码'
                    rules={[
                        {
                            type: 'string',
                            message: '企业代码不可以为空',
                            required: true
                        }
                    ]}
                >
                    <Input/>
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

