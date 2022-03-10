/**
 * @Description: -TODO
 * @Author ZhangWei
 * @Date   2021/6/28 15:05
 * @Version V1.0
 */
import React, {useEffect, useState} from 'react';
import {Button, Space, Table} from "antd";
import {Button as ElButton} from "element-react";
import AdminForm from "./AdminForm";
import companyApi from "../../http/api/CompanyApi";
import DeleteCompany from "./DeleteCompany";
import {CompanyEntity} from "../../entity";
import {deepCopy} from "../../util";

export default function Company() {

    /**
     * 表格行头
     */
    const [tableColumn] = useState([
        {
            title: '行号',
            render: (text: any, record: any, index: number) => `${index + 1}`,
        },
        {title: '公司名', dataIndex: 'company', key: 'company'},
        {title: '企业代码', dataIndex: 'code', key: 'code'},
        {title: '创建日期', dataIndex: 'createTime', key: 'createTime'},
        {
            title: '操作', key: 'action', width: '32px',
            render: (item: CompanyEntity) => (
                <Space>
                    <ElButton type="success" onClick={() => {
                        beforeEdit(item)
                    }}>修改</ElButton>
                    <DeleteCompany id={item.id} handleDelete={freshTable}/>
                </Space>
            )
        }
    ]);

    /**
     * 表格数据
     */
    const [companyList, setCompanyList] = useState<Array<CompanyEntity>>([]);

    /**
     * 表格加载
     */
    const [tableLoading, setTableLoading] = useState(true);

    /**
     * form可视
     */
    const [formSeem, setFormSeem] = useState<boolean>(false);

    // @ts-ignore
    const [editData, setEditData] = useState<CompanyEntity>({});

    /**
     * 更改form可视
     * @param flag
     */
    function editFormSeem(flag: boolean) {
        setFormSeem(flag);
    }

    /**
     *加载企业数据
     */
    async function initCompanyList() {
        //await是同步代码，相当于加排它锁，必须先运行玩await代码
        let resData: Array<CompanyEntity> = deepCopy(await companyApi.list());
        setTableLoading(true);
        //回滚
        setTimeout(() => {
            setCompanyList(resData);
            setTableLoading(false);
        }, 500)
    }

    /**
     * 完成添加后后续动作
     */
    function freshTable() {
        setFormSeem(false);
        initCompanyList();
    }
    //修改前，item里面是一条company的信息
    function beforeEdit(item: CompanyEntity) {
        setEditData(item);
        editFormSeem(true)
    }

    function beforeAdd(){
        // @ts-ignore
        setEditData({});
        editFormSeem(true)
    }


    useEffect(() => {
        initCompanyList();
    },[]);

    return (
        <div style={{marginRight: "16px"}}>

            <Button type='primary'
                    onClick={beforeAdd}
            >添加企业</Button>

            <AdminForm visible={formSeem}
                       formData={editData}
                       cancel={() => editFormSeem(false)}
                       finish={freshTable}/>

            <Table
                dataSource={companyList}
                rowKey={'id'}
                columns={tableColumn}
                loading={tableLoading}
                pagination={{
                    defaultPageSize: 5,
                    total: companyList.length,
                    showSizeChanger: true,
                    pageSizeOptions: ["5", "10", "25", "50"],

                }}
            >
            </Table>

        </div>
    );
}

