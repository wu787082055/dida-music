/**
 * @Description: -TODO
 * @Author ZhangWei
 * @Date   2021/6/28 15:05
 * @Version V1.0
 */
import React, {useEffect, useState} from 'react';
import {Button, Space, Table} from "antd";
import userApi from "../../http/api/UserApi";

import {Button as ElButton} from "element-react";
import UserAddForm from "./UserAddForm";


export default function User() {
    /*表头*/
    const [tableColumn] = useState([
        {
            title: "行号",
            render: (text, record, index) => `${index + 1}`,
        },
        {title: '用户名', dataIndex: 'username', key: 'username'},
        {title: '人员代码', dataIndex: 'peopleCode', key: 'peopleCode'},
        {title: '公司代码', dataIndex: 'companyCode', key: 'companyCode'},
        {
            title: '操作', key: 'action', width: '32px',
            render: (item) => (
                <Space>
                    <ElButton type="success" onClick={() => {

                    }}>修改</ElButton>
                </Space>
            )
        }
    ]);

    //表格内数据
    const [tableList, setTableList] = useState([]);

    //加载特效
    const [tableLoading, setTableLoading] = useState(false);

    const [formSeem, setFormSeem] = useState(false);


    async function initTableData() {
        let data = await userApi.list();
        setTableLoading(true);
        //回滚
        setTimeout(() => {
            setTableList(data);
            setTableLoading(false);
        }, 500)
    }


    function editFormSeem(bool) {
        return () => {
            setFormSeem(bool);
        }
    }

    function freshTable() {
        //editFormSeem(false);
        setFormSeem(false);
        initTableData();
    }


    useEffect(() => {
        initTableData();
    }, []);

    return (
        <div style={{marginRight: "16px"}}>


            <Button type='primary'
                    onClick={editFormSeem(true)}
            >添加用户</Button>


            <UserAddForm visible={formSeem}
                         cancel={editFormSeem(false)}
                         finish={freshTable}/>

            <Table
                dataSource={tableList}
                rowKey={'id'}
                columns={tableColumn}
                loading={tableLoading}

                pagination={{
                    defaultPageSize: 5,
                    total: tableList.length,
                    showSizeChanger: true,
                    pageSizeOptions: ["5", "10", "25", "50"],

                }}
            >
            </Table>
        </div>
    );
}

